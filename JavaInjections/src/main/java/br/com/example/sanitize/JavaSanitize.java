/*
 *
 * Injection
 * JavaSanitize
 * @since 15/07/2021
 */
package br.com.example.sanitize;


import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

/**
 * https://github.com/OWASP/java-html-sanitizer/tree/master
 */
public class JavaSanitize {

    public String sanitize(String outputToUser){
        /* Create a sanitizing policy that only allow tag '<p>' and '<strong>'*/
        PolicyFactory policy = new HtmlPolicyBuilder().allowElements(
                "address", "article", "aside", "footer", "header", "h1", "h2", "h3", "h4",
                "h5", "h6", "hgroup", "main", "nav", "section", "blockquote", "dd", "div",
                "dl", "dt", "figcaption", "figure", "hr", "li", "main", "ol", "p", "pre",
                "ul", "a", "abbr", "b", "bdi", "bdo", "br", "cite", "code", "data", "dfn",
                "em", "i", "kbd", "mark", "q", "rb", "rp", "rt", "rtc", "ruby", "s", "samp",
                "small", "span", "strong", "sub", "sup", "time", "u", "var", "wbr", "caption",
                "col", "colgroup", "table", "tbody", "td", "tfoot", "th", "thead", "tr").toFactory();

        /* Sanitize the output that will be sent to user*/
        String safeOutput = policy.sanitize(outputToUser);
        return safeOutput;
    }

    public static void main(String[] args) {
        String outputToUser = "You <p>user login</p> is <strong>owasp-user01</strong>";
        outputToUser += "<script>alert(22);</script><img src='#' onload='javascript:alert(23);'>";
        System.out.println(new JavaSanitize().sanitize(outputToUser));
    }
}
