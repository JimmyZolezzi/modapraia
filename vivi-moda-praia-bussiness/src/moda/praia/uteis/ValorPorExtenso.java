package moda.praia.uteis;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Formatter;
import java.text.DecimalFormatSymbols;

/**
 * <p>
 * <b>Classe:</b> Extenso
 * </p>
 *
 * <p>
 * <b>Descri��o:</b> O programa converte um n�mero para o seu valor em extenso.
 * </p>
 *
 * <p>
 * <b>Coment�rios:</b> A convers�o aceita apenas valores monet�rios,
 *
 * portanto a casa centezimal (dos centavos) admite at� 3 d�gitos
 *
 * com o extenso arredondando em somente 2 d�gitos e
 *
 * a parte inteira admite at� 9 grupos, chegando a casa dos septilh�es.
 *
 * Por�m valores negativos n�o ser�o aceitos, somente n�meros positivos.
 * </p>
 *
 * <p>
 * <b>Exemplo:</b> R$ 345.678.901.234.567.890.123.456.789,01
 *
 * trezentos e quarenta e cinco septilh�es e
 *
 * seiscentos e setenta e oito sextilh�es e
 *
 * novecentos e um quintilh�es e
 *
 * duzentos e trinta e quatro quatrilh�es e
 *
 * quinhentos e sessenta e sete trilh�es e
 *
 * oitocentos e noventa bilh�es e
 *
 * cento e vinte e tr�s milh�es e
 *
 * quatrocentos e cinquenta e seis mil e
 *
 * setecentos e oitenta e nove reais e
 *
 * um centavo.
 * </p>
 *
 */
public class ValorPorExtenso {

	private ArrayList<Integer> nro;

	private BigInteger num;

	private BigDecimal valorMonetario;

	private String Qualificadores[][] = { { "Centavo", "Centavos" },
			{ "", "" }, { "Mil", "Mil" }, { "Milh�o", "Milh�es" },
			{ "Bilh�o", "Bilh�es" }, { "Trilh�o", "Trilh�es" },
			{ "Quatrilh�o", "Quatrilh�es" }, { "Quintilh�o", "Quintilh�es" },
			{ "Sextilh�o", "Sextilh�es" }, { "Septilh�o", "Septilh�es" } };

	private String Numeros[][] = {
			{ "Zero", "Um", "Dois", "Tr�s", "Quatro", "Cinco", "Seis", "Sete",
					"Oito", "Nove", "Dez", "Onze", "Doze", "Treze", "Quatorze",
					"Quinze", "Desesseis", "Desessete", "Dezoito", "Desenove" },
			{ "Vinte", "Trinta", "Quarenta", "Cinquenta", "Sessenta",
					"Setenta", "Oitenta", "Noventa" },
			{ "Cem", "Cento", "Duzentos", "Trezentos", "Quatrocentos",
					"Quinhentos", "Seiscentos", "Setecentos", "Oitocentos",
					"Novecentos" } };

	/**
	 * <p>
	 * Construtor.
	 * </p>
	 *
	 * @since JDK 1.5
	 */
	public ValorPorExtenso() {
		nro = new ArrayList<Integer>();
	}

	/**
	 * <p>
	 * Construtor.
	 * </p>
	 *
	 * @param dec
	 *            Valor para colocar por extenso.
	 * @since JDK 1.5
	 */
	public ValorPorExtenso(BigDecimal dec) throws Exception {
		this();
		setNumber(dec);
	}

	/**
	 * <p>
	 * Construtor.
	 * </p>
	 *
	 * @param dec
	 *            Valor para colocar por extenso.
	 * @since JDK 1.5
	 */
	public ValorPorExtenso(double dec) throws Exception {
		this();
		setNumber(dec);
	}

	/**
	 * <p>
	 * Seta o atributo Number.
	 * </p>
	 *
	 * @param dec
	 *            O novo valor para Number.
	 * @since JDK 1.5
	 */
	public void setNumber(BigDecimal dec) throws Exception {

		// Mant�m o valor informado no escopo da classe para utiliza��o
		// posterior
		// pelo m�todo DecimalFormat desta mesma classe.
		valorMonetario = dec;

		// Se o valor informado for negativo ou maior que 999 septilh�es,
		// dispara uma exce��o.
		BigDecimal maxNumber = new BigDecimal("999999999999999999999999999.99");
		if ((dec.signum() == -1) || (dec.compareTo(maxNumber) == 1)) {
			throw new Exception(
					"\nNao sao suportados numeros negativos ou maiores que 999 septilhoes para a conversao de valores monetarios."
							+ "\nNumeros validos vao de 0,00 at� 999.999.999.999.999.999.999.999.999,99"
							+ "\nO numero informado foi: " + DecimalFormat());
		}

		// Converte para inteiro arredondando os centavos.
		num = dec.setScale(2, BigDecimal.ROUND_HALF_UP).multiply(
				BigDecimal.valueOf(100)).toBigInteger();

		// Adiciona valores.
		nro.clear();
		if (num.equals(BigInteger.ZERO)) {
			// Centavos.
			nro.add(new Integer(0));
			// Valor.
			nro.add(new Integer(0));
		} else {
			// Adiciona centavos.
			addRemainder(100);

			// Adiciona grupos de 1000.
			while (!num.equals(BigInteger.ZERO)) {
				addRemainder(1000);
			}
		}
	}

	/**
	 * <p>
	 * Seta o atributo Number.
	 * </p>
	 *
	 * @param dec
	 *            O novo valor para Number.
	 * @since JDK 1.5
	 */
	public void setNumber(double dec) throws Exception {
		setNumber(new BigDecimal(dec));
	}

	/**
	 * <p>
	 * Mostra o valor por extenso no console.
	 * </p>
	 *
	 * @since JDK 1.5
	 */
	public void show() {
		Iterator<Integer> valores = nro.iterator();

		while (valores.hasNext()) {
			System.out.println(valores.next().intValue());
		}
		System.out.println(toString());
	}

	/**
	 * <p>
	 * Mostra o valor monet�rio formatado pelo International Locale Default.
	 * </p>
	 *
	 * @return String O valor monet�rio (num�rico) formatado pelo International
	 *         Locale Default.
	 * @since JDK 1.5
	 */
	public String DecimalFormat() {
		// A classe Formatter() incluida desde o JDK 1.5.0 tem que ser utilizada
		// devido
		// a class DecimalFormat() n�o suportar o tipo BigDecimal(),
		// somente o tipo primitivo double.
		// System.out.println("Numero : " + (new
		// DecimalFormat().format(Double.valueOf(args[0]))));
		Formatter formatter = new Formatter();
		DecimalFormatSymbols sym = new DecimalFormatSymbols();
		Object[] objs = new Object[1];
		objs[0] = valorMonetario;
		formatter.format("%-,27.2f", objs);

		// retorna o n�mero informado no formato de valor monet�rio,
		// International Locale Default.
		return sym.getCurrencySymbol() + " " + formatter.toString();
	}

	/**
	 * <p>
	 * Mostra o valor do n�mero por extenso.
	 * </p>
	 *
	 * @return String com o valor do n�mero por extenso.
	 * @since JDK 1.5
	 */
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();

		int numero = nro.get(0).intValue();
		int ct;

		for (ct = nro.size() - 1; ct > 0; ct--) {
			// Se ja existe texto e o atual n�o � zero
			if (buf.length() > 0 && !ehGrupoZero(ct)) {
				buf.append(" e ");
			}
			buf.append(numToString(nro.get(ct).intValue(), ct));
		}
		if (buf.length() > 0) {
			if (ehUnicoGrupo())
				buf.append(" de ");
			while (buf.toString().endsWith(" "))
				buf.setLength(buf.length() - 1);
			if (ehPrimeiroGrupoUm())
				buf.insert(0, "h");
			if (nro.size() == 2 && nro.get(1).intValue() == 1) {
				buf.append(" Real");
			} else {
				buf.append(" Reais");
			}
			if (nro.get(0).intValue() != 0) {
				buf.append(" e ");
			}
		}
		if (nro.get(0).intValue() != 0) {
			buf.append(numToString(nro.get(0).intValue(), 0));
		}
		return buf.toString();
	}

	/**
	 * <p>
	 * Identifica se a quantidade de d�gitos do primeiro grupo do valor for
	 * igual a uma unidade.
	 * </p>
	 *
	 * @return boolean <code><b>true</b></code> se a quantidade de d�gitos do
	 *         primeiro grupo for igual a 1. <code><b>false</b></code> se a
	 *         quantidade de d�gitos do primeiro grupo for maior que 1.
	 * @since JDK 1.5
	 */
	private boolean ehPrimeiroGrupoUm() {
		if (nro.get(nro.size() - 1).intValue() == 1)
			return true;
		return false;
	}

	/**
	 * <p>
	 * Adiciona uma caracter�stica para o atributo Remainder (resto de uma
	 * divis�o).
	 * </p>
	 *
	 * @param divisor
	 *            A caracter�stica a ser adicionada ao atributo Remainder.
	 * @since JDK 1.5
	 */
	private void addRemainder(int divisor) {
		// Encontra newNum[0] = num m�dulo divisor, newNum[1] = num dividido
		// divisor.
		BigInteger[] newNum = num.divideAndRemainder(BigInteger
				.valueOf(divisor));

		// Adiciona m�dulo.
		nro.add(new Integer(newNum[1].intValue()));

		// Altera n�mero.
		num = newNum[0];
	}

	/**
	 * <p>
	 * Identifica se o n�mero possui mais grupos.
	 * </p>
	 * <p>
	 * Obs.: Grupos s�o tamb�m conhecidos por casas, assim como, casas decimais,
	 * casas de milhar, milh�o, bilh�o, trilh�o, etc.
	 * </p>
	 *
	 * @param ps
	 *            posi��o do d�gito no n�mero.
	 * @return boolean <code><b>true</b></code> se a posi��o do d�gito no
	 *         n�mero conter um valor maior que 1. <code><b>false</b></code>
	 *         se a posi��o do d�gito no n�mero conter um valor igual a zero.
	 * @since JDK 1.5
	 */
	private boolean temMaisGrupos(int ps) {
		for (; ps > 0; ps--) {
			if (nro.get(ps).intValue() != 0)
				return true;
		}
		return false;
	}

	/**
	 * <p>
	 * Identifica se o grupo corrente � o �ltimo a ser convertido por extenso.
	 * </p>
	 * <p>
	 * Obs.: Grupos s�o tamb�m conhecidos por casas, assim como, casas decimais,
	 * casas de milhar, milh�o, bilh�o, trilh�o, etc.
	 * </p>
	 *
	 * @param ps
	 *            posi��o do d�gito no n�mero.
	 * @return boolean <code><b>true</b></code> se a posi��o do d�gito no
	 *         n�mero conter um valor maior que 1. <code><b>false</b></code>
	 *         se a posi��o do d�gito no n�mero conter um valor igual a zero.
	 * @since JDK 1.5
	 */
	private boolean ehUltimoGrupo(int ps) {
		return (ps > 0) && nro.get(ps).intValue() != 0
				&& !temMaisGrupos(ps - 1);
	}

	/**
	 * <p>
	 * Identifica se o n�mero s� possui a casa decimal a ser convertida por
	 * extenso, al�m da casa centezimal (dos centavos) obviamente.
	 * </p>
	 * <p>
	 * Obs.: Grupos s�o tamb�m conhecidos por casas, assim como, casas decimais,
	 * casas de milhar, milh�o, bilh�o, trilh�o, etc.
	 * </p>
	 *
	 * @return boolean <code><b>true</b></code> se a posi��o do d�gito no
	 *         n�mero conter um valor maior que 1. <code><b>false</b></code>
	 *         se a posi��o do d�gito no n�mero conter um valor igual a zero.
	 * @since JDK 1.5
	 */
	private boolean ehUnicoGrupo() {
		if (nro.size() <= 3)
			return false;
		if (!ehGrupoZero(1) && !ehGrupoZero(2))
			return false;
		boolean hasOne = false;
		for (int i = 3; i < nro.size(); i++) {
			if (nro.get(i).intValue() != 0) {
				if (hasOne)
					return false;
				hasOne = true;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Identifica se o n�mero n�o possui a casa decimal (valor inteiro igual a
	 * zero) restando apenas a casa centezimal (dos centavos) a ser convertida
	 * por extenso.
	 * </p>
	 * <p>
	 * Obs.: Grupos s�o tamb�m conhecidos por casas, assim como, casas decimais,
	 * casas de milhar, milh�o, bilh�o, trilh�o, etc.
	 * </p>
	 *
	 * @param ps
	 *            posi��o do d�gito no n�mero.
	 * @return boolean <code><b>true</b></code> se a posi��o do d�gito no
	 *         n�mero conter um valor maior que 1. <code><b>false</b></code>
	 *         se a posi��o do d�gito no n�mero conter um valor igual a zero.
	 * @since JDK 1.5
	 */
	private boolean ehGrupoZero(int ps) {
		if (ps <= 0 || ps >= nro.size())
			return true;
		return nro.get(ps).intValue() == 0;
	}

	/**
	 * <p>
	 * Converte um n�mero em uma String para identificar o posicionamento de
	 * seus d�gitos.
	 * </p>
	 *
	 * @param numero
	 *            Parte inteira do n�mero (do valor monet�rio).
	 * @param escala
	 *            Parte centezimal do n�mero (dos centavos).
	 * @return String O n�mero informado convertido em String.
	 * @since JDK 1.5
	 */
	private String numToString(int numero, int escala) {
		int unidade = (numero % 10);
		int dezena = (numero % 100); // * nao pode dividir por 10 pois
										// verifica
		// de 0..19
		int centena = (numero / 100);

		StringBuffer buf = new StringBuffer();

		if (numero != 0) {
			if (centena != 0) {
				if (dezena == 0 && centena == 1) {
					buf.append(Numeros[2][0]);
				} else {
					buf.append(Numeros[2][centena]);
				}
			}

			if ((buf.length() > 0) && (dezena != 0)) {
				buf.append(" e ");
			}
			if (dezena > 19) {
				dezena /= 10;
				buf.append(Numeros[1][dezena - 2]);
				if (unidade != 0) {
					buf.append(" e ");
					buf.append(Numeros[0][unidade]);
				}
			} else if (centena == 0 || dezena != 0) {
				buf.append(Numeros[0][dezena]);
			}

			buf.append(" ");
			if (numero == 1) {
				buf.append(Qualificadores[escala][0]);
			} else {
				buf.append(Qualificadores[escala][1]);
			}
		}

		return buf.toString();
	}

	public static void main(String[] args) throws Exception {
		// Converte do tipo BigDecimal para a String por extenso.
		ValorPorExtenso teste = new ValorPorExtenso(new BigDecimal(55000.69));

		// Mostra o n�mero informado no formato de valor monet�rio.
		System.out.println("Numero  : " + teste.DecimalFormat());

		// Mostra o n�mero informado no formato de valor por extenso.
		System.out.println("Extenso : " + teste.toString());
	}
}