package com.google.gdata.util.common.base;

/**
 * An object that converts literal text into a format safe for inclusion in a particular context (such as an XML
 * document). Typically (but not always), the inverse process of "unescaping" the text is performed automatically by the
 * relevant parser.
 * 
 * <p>
 * For example, an XML escaper would convert the literal string {@code "Foo<Bar>"} into {@code "Foo&lt;Bar&gt;"} to
 * prevent {@code "<Bar>"} from being confused with an XML tag. When the resulting XML document is parsed, the parser
 * API will return this text as the original literal string {@code "Foo<Bar>"}.
 * 
 * <p>
 * An {@code Escaper} instance is required to be stateless, and safe when used concurrently by multiple threads.
 * 
 * <p>
 * Several popular escapers are defined as constants in the class {@link CharEscapers}. To create your own escapers, use
 * {@link CharEscaperBuilder}, or extend {@link CharEscaper} or {@code UnicodeEscaper}.
 * 
 * 
 */
public interface Escaper {
	/**
	 * Returns the escaped form of a given literal string.
	 * 
	 * <p>
	 * Note that this method may treat input characters differently depending on the specific escaper implementation.
	 * <ul>
	 * <li>{@link UnicodeEscaper} handles <a href="http://en.wikipedia.org/wiki/UTF-16">UTF-16</a> correctly, including
	 * surrogate character pairs. If the input is badly formed the escaper should throw {@link IllegalArgumentException}.
	 * <li>{@link CharEscaper} handles Java characters independently and does not verify the input for well formed
	 * characters. A CharEscaper should not be used in situations where input is not guaranteed to be restricted to the
	 * Basic Multilingual Plane (BMP).
	 * </ul>
	 * 
	 * @param string
	 *            the literal string to be escaped
	 * @return the escaped form of {@code string}
	 * @throws NullPointerException
	 *             if {@code string} is null
	 * @throws IllegalArgumentException
	 *             if {@code string} contains badly formed UTF-16 or cannot be escaped for any other reason
	 */
	public String escape(String string);
}
