// Generated from Outis.g4 by ANTLR 4.7.1
package parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link OutisParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface OutisVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link OutisParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(OutisParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link OutisParser#event}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEvent(OutisParser.EventContext ctx);
	/**
	 * Visit a parse tree produced by {@link OutisParser#text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitText(OutisParser.TextContext ctx);
}