import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalizadorLexico {

    public ArrayList<Token> analizarCodigo(String codigo) {
        ArrayList<Token> tokens = new ArrayList<>();

        // Patrones para reconocer palabras clave, identificadores y símbolos
        String patronPalabraClave = "\\b(nebulizar|cúmulo|fragmento|intng|doubleng|charng|stringNG|boolng)\\b";
        String patronIdentificador = "\\b[a-zA-Z][a-zA-Z0-9]*\\b";
        String patronSimbolo = "[(){}\\[\\]+\\-*/=]";

        // Combinar patrones en una expresión regular
        String patronCompleto = String.format("(%s|%s|%s)", patronPalabraClave, patronIdentificador, patronSimbolo);

        // Crear objetos Pattern y Matcher
        Pattern pattern = Pattern.compile(patronCompleto);
        Matcher matcher = pattern.matcher(codigo);

        // Analizar el código fuente
        while (matcher.find()) {
            String valor = matcher.group();
            String tipo = determinarTipo(valor);
            tokens.add(new Token(tipo, valor));
        }

        return tokens;
    }

    private String determinarTipo(String valor) {
        // Lógica para determinar el tipo de token
        switch (valor) {
            case "nebulizar":
            case "cúmulo":
            case "fragmento":
            case "intng":
            case "doubleng":
            case "charng":
            case "stringNG":
            case "boolng":
                return "PALABRA_CLAVE";
            case "+":
            case "-":
            case "*":
            case "/":
            case "=":
            case "[":
            case "]":
            case "{":
            case "}":
            case "(":
            case ")":
                return "SIMBOLO";
            default:
                if (valor.matches("\\b[a-zA-Z][a-zA-Z0-9]*\\b")) {
                    return "ELEMENTO";
                } else {
                    return "DESCONOCIDO";
                }
        }
    }
}
