/*
 * generated by Xtext 2.20.0
 */
package org.xtext.typhonql.parser.antlr;

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class TyphonQLAntlrTokenFileProvider implements IAntlrTokenFileProvider {

	@Override
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader.getResourceAsStream("org/xtext/typhonql/parser/antlr/internal/InternalTyphonQL.tokens");
	}
}