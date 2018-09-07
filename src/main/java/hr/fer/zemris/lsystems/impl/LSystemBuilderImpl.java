package hr.fer.zemris.lsystems.impl;

import java.awt.Color;

import hr.fer.zemris.lsystems.LSystem;
import hr.fer.zemris.lsystems.LSystemBuilder;
import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.commands.ColorCommand;
import hr.fer.zemris.lsystems.impl.commands.DrawCommand;
import hr.fer.zemris.lsystems.impl.commands.PopCommand;
import hr.fer.zemris.lsystems.impl.commands.PushCommand;
import hr.fer.zemris.lsystems.impl.commands.RotateCommand;
import hr.fer.zemris.lsystems.impl.commands.ScaleCommand;
import hr.fer.zemris.lsystems.impl.commands.SkipCommand;
import hr.fer.zemris.math.Vector2D;
import hr.zemris.java.custom.collections.Dictionary;

/**
 * A custom implementation of an L-system builder. With the given origin, angle,
 * unit length and scale parameters, along with the commands, productions and
 * the axiom, it creates an L-system with various possible levels. The system is
 * then drawn using a painter interface implementation.
 * 
 * @author 0036502252
 *
 */
public class LSystemBuilderImpl implements LSystemBuilder {
	/**
	 * Keeps all the registered commands mapped to their symbol representations.
	 */
	private Dictionary registeredCommands;
	/**
	 * Keeps all the registered productions mapped to the symbols the production
	 * will replace.
	 */
	private Dictionary registeredProductions;
	/**
	 * Unit length used for drawing.
	 */
	private double unitLength;
	/**
	 * Scales the unit length according to the degree of production.
	 */
	private double unitLengthDegreeScaler;
	/**
	 * The vector at which the origin is initially situated.
	 */
	private Vector2D origin;
	/**
	 * The angle at which the origin is initially orientated.
	 */
	private double angle;
	/**
	 * The axiom of the system, from which all productions are created.
	 */
	private String axiom;

	/**
	 * Constructs an implementation of the L-system builder with its default
	 * values.
	 */
	public LSystemBuilderImpl() {
		this.registeredProductions = new Dictionary();
		this.registeredCommands = new Dictionary();
		this.unitLength = 0.1;
		this.unitLengthDegreeScaler = 1;
		this.origin = new Vector2D(0, 0);
		this.angle = 0;
		this.axiom = "";
	}

	/**
	 * Builds and then draws an L-system, using the parameters from the L-system
	 * implementation (its axiom, origin vector, production and commands).
	 */
	@Override
	public LSystem build() {
		return new LSystem() {

			/**
			 * Generates a string representation of an l-system generation for
			 * the given level.
			 * 
			 * @param level
			 *            the level of generation
			 * @return the (k-1)-th generation for the k-th level
			 */
			@Override
			public String generate(int level) {
				String generation = axiom;
				for (int i = 1; i <= level; i++) {
					StringBuilder sb = new StringBuilder();
					for (char symbol : generation.toCharArray()) {
						String prod = (String) registeredProductions
								.get(symbol);
						if (prod == null) {
							sb.append(symbol);
						} else {
							sb.append(prod);
						}
					}
					generation = sb.toString();
				}
				return generation;
			}

			/**
			 * Draws the l-system generation.
			 * 
			 * @param level
			 *            the level of generation
			 * @painter the painter used for line drawing
			 */
			@Override
			public void draw(int level, Painter painter) {
				Context ctx = new Context();
				Vector2D direction = new Vector2D(1, 0).rotated(angle);

				TurtleState state = new TurtleState(origin, direction,
						Color.BLACK, unitLength);
				ctx.pushState(state);

				char[] data = generate(level).toCharArray();

				state.setUnitLength(
						unitLength * Math.pow(unitLengthDegreeScaler, level));

				for (int i = 0, n = data.length; i < n; i++) {
					char currentSymbol = data[i];
					Command command = (Command) registeredCommands
							.get(currentSymbol);
					if (command != null) {
						command.execute(ctx, painter);
					}
				}
			}
		};
	}

	/**
	 * From a given text string, configures the parameters and sets them to a
	 * new {@link LSystemBuilder}.
	 * 
	 * @param lines
	 *            the lines from which the parameters, commands and productions
	 *            are configured
	 * @return the new {@link LSystemBuilder} with the given parameters
	 * @throws IllegalArgumentException
	 *             if there is a wrong number of arguments in a command
	 * @throws NumberFormatException
	 *             if a certain number cannot be parsed
	 */
	@Override
	public LSystemBuilder configureFromText(String[] lines) {
		for (String line : lines) {
			line = skipMultipleSpaces(line);

			String[] args = line.split(" ");

			switch (args[0]) {
			case "origin":
				if (args.length != 3)
					throw new IllegalArgumentException(
							"An 'origin' directive must contain exactly 3 parameters, separated by spaces!!");
				double x = Double.parseDouble(args[1]);
				double y = Double.parseDouble(args[2]);
				setOrigin(x, y);
				break;
			case "angle":

				if (args.length != 2)
					throw new IllegalArgumentException(
							"An 'angle' directive must contain exactly 2 parameters, separated by spaces!");
				double angle = Double.parseDouble(args[1]);
				setAngle(angle);
				break;
			case "unitLength":
				if (args.length != 2)
					throw new IllegalArgumentException(
							"A 'unitLength' directive must contain exactly 2 parameters, separated by spaces!");
				double unitLength = Double.parseDouble(args[1]);
				setUnitLength(unitLength);
				break;
			case "axiom":
				/*
				 * set the axiom to the line which comes after the 'axiom' part
				 * of the directive
				 */
				setAxiom(line.substring(args[0].length()));
				break;
			case "production":
				if (args.length != 3)
					throw new IllegalArgumentException(
							"A 'production' directive must contain exactly 3 parameters, separated by spaces!");
				registerProduction(args[1].toCharArray()[0], args[2]);
				break;
			case "command":
				/*
				 * A command can have either 3 or 4 arguments; for example, the
				 * 'pop' command contains 3, while the draw contains 4.
				 */
				if (args.length == 4) {
					registerCommand(args[1].toCharArray()[0],
							args[2] + " " + args[3]);
				} else if (args.length == 3) {
					registerCommand(args[1].toCharArray()[0], args[2]);
				} else {
					throw new IllegalArgumentException(
							"Wrong number of arguments in the command! Must be 3 or 4!");
				}
				break;
			case "unitLengthDegreeScaler":
				line = line.substring(line.lastIndexOf('r') + 1); // do kraja
																	// ID-a
				if (line.contains("/")) {
					args = line.trim().split("/");
					double a = Double.parseDouble(args[0]);
					double b = Double.parseDouble(args[1]);
					setUnitLengthDegreeScaler(a / b);
				} else {
					setUnitLengthDegreeScaler(Double.parseDouble(line));
				}
				break;
			case "":
				break;
			default:
				throw new IllegalArgumentException(
						"Illegal entry! Cannot recognize the parameter/command."
								+ "\nNote: all inputs are case-sensitive.");
			}
		}
		return this;
	}

	/**
	 * Creates a new command according to the string input and adds it to the
	 * {@link LSystemBuilder}'s dictionary.
	 * 
	 * @param symbol
	 *            the symbol used as the key for the command
	 * @action the string representation of the command to be parsed
	 * @return the {@link LSystemBuilder} with its modified dictionary
	 * 
	 */
	@Override
	public LSystemBuilder registerCommand(char symbol, String action) {
		String[] args = action.split(" ");
		switch (args[0].toLowerCase()) {
		case "draw":
			double drawStep = Double.parseDouble(args[1]);
			this.registeredCommands.put(symbol, new DrawCommand(drawStep));
			break;
		case "skip":
			double skipStep = Double.parseDouble(args[1]);
			this.registeredCommands.put(symbol, new SkipCommand(skipStep));
			break;
		case "scale":
			double factor = Double.parseDouble(args[1]);
			this.registeredCommands.put(symbol, new ScaleCommand(factor));
			break;
		case "rotate":
			double angle = Double.parseDouble(args[1]);
			this.registeredCommands.put(symbol, new RotateCommand(angle));
			break;
		case "push":
			this.registeredCommands.put(symbol, new PushCommand());
			break;
		case "pop":
			this.registeredCommands.put(symbol, new PopCommand());
			break;
		case "color":
			String rgb = "0x" + args[1];
			this.registeredCommands.put(symbol,
					new ColorCommand(Color.decode(rgb)));
			break;
		default:
			break;
		}

		return this;
	}

	/**
	 * Adds the production to the {@link LSystemBuilder}'s productions
	 * dictionary.
	 * 
	 * @param symbol
	 *            the symbol used as the key for the production
	 * @param production
	 *            the production string to be stored
	 * @return the {@link LSystemBuilder} with its modified dictionary
	 */
	@Override
	public LSystemBuilder registerProduction(char symbol, String production) {
		this.registeredProductions.put(symbol, production);
		return this;
	}

	/**
	 * Sets the angle of the {@link LSystemBuilder}.
	 * 
	 * @param angle
	 *            the angle to be set, in degrees.
	 * @return the {@link LSystemBuilder} with its modified angle parameter
	 */
	@Override
	public LSystemBuilder setAngle(double angle) {
		this.angle = angle;
		return this;
	}

	/**
	 * Sets the axiom of the {@link LSystemBuilder}.
	 * 
	 * @param axiom
	 *            the axiom to be set
	 * @return the {@link LSystemBuilder} with its modified axiom
	 */
	@Override
	public LSystemBuilder setAxiom(String axiom) {
		this.axiom = axiom;
		return this;
	}

	/**
	 * Sets the origin vector of the {@link LSystemBuilder}.
	 * 
	 * @param x
	 *            the x component of the vector
	 * @param y
	 *            the y component of the vector
	 * @return the {@link LSystemBuilder} with its modified origin vector
	 */
	@Override
	public LSystemBuilder setOrigin(double x, double y) {
		this.origin = new Vector2D(x, y);
		return this;
	}

	/**
	 * Sets the unit length of the {@link LSystemBuilder}.
	 * 
	 * @param unitLength
	 *            the unit length to be setž
	 * @return the {@link LSystemBuilder} with its modified unit length
	 */
	@Override
	public LSystemBuilder setUnitLength(double unitLength) {
		this.unitLength = unitLength;
		return this;
	}

	/**
	 * Sets the unit length scaler of the {@link LSystemBuilder}.
	 * 
	 * @param unitLengthDegreeScaler
	 *            the unit length scaler to be set
	 * @return the {@link LSystemBuilder} with its modified unit length scaler
	 */
	@Override
	public LSystemBuilder setUnitLengthDegreeScaler(
			double unitLengthDegreeScaler) {
		this.unitLengthDegreeScaler = unitLengthDegreeScaler;
		return this;
	}

	/**
	 * Private utility method. If there are multiple spaces or a tab in a
	 * string, it replaces them with a single space.
	 * 
	 * @param line
	 * @return
	 */
	private static String skipMultipleSpaces(String line) {
		String newLine = line.replaceAll("\\s+", " ").replaceAll("\t", "");
		return newLine;
	}
}
