package littleg;

/** Copyright (c) 2020 Sean Beecroft,
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * @author Sean Beecroft (aka xagau) https://www.github.com/xagau
 *
 */

public enum Token {
    PLUS, // +
    MINUS, // -
    MULTIPLY, // *
    DIVIDE, // /
    INCREMENT, // ++
    DECREMENT, // --
    ASSIGNMENT, // =
    GT, // >
    LT, // <
    GTE, // >=
    LTE, // <=
    EQUAL, // ==
    NOT, // !
    NOT_EQUAL, // !=
    OPEN_BLOCK, // {
    CLOSE_BLOCK, // }
    OPEN_BRACE, // (
    CLOSE_BRACE, // )
    AND, // &
    OR, // |
    COMMA, // ,
    END_STATEMENT, // ;
    TERMINATE,
    MEMBER_OF, // .
    OPTION_OF, // :
    OPEN_INDEX, // [
    CLOSE_INDEX, // ]
    LOGICAL_AND, // &&
    LOGICAL_OR, // ||
    CRLF, // \n
    NOT_A_TOKEN;
}
