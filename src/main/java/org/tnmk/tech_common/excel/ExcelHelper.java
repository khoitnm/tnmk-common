package org.tnmk.tech_common.excel;

public class ExcelHelper {
    private static final char LETTERS_IN_EN_ALFABET = 26;
    private static final char BASE = LETTERS_IN_EN_ALFABET;
    private static final char A_LETTER = 65;

    /**
     * Replaces occurences of the text representation of columnIndex to columnIndex+1.
     * Ex:
     * "B6:B8" (columnIndex = 2) -> "C6:C8" (columnIndex = 3)
     *
     * @param cellFormula
     * @param originalColumnIndex
     * @return
     */
    public static String updateFormula(String cellFormula, int originalColumnIndex, int newColumnIndex) {
        String existingColName = getReferenceForColumnIndex(originalColumnIndex);
        String newColName = getReferenceForColumnIndex(newColumnIndex);
        //FIXME doesn't work correctly when the column name has more than one characters (e.g. AA, AB, AC...)
        String newCellFormula = cellFormula.replace(existingColName, newColName);
        return newCellFormula;
    }

    /**
     * Does a "Base 26" - Base26E transformation on the given index, to obtain the alphabet representation.
     * The transformation is not exactly Base26, since the factor for each degree of power (besides first)
     * is represented as "1 less".
     * <p>
     * Ex:
     * 25 -> Z
     * 26 -> BA (in Base26) -> AA (in Excel)
     * 27 -> BB (in Base26) -> AB (in Excel)
     * (we have B instead of A for degree of power 1)
     * So a normal 'AACAAA' in Base26 is 'BBDBBA' in Base26E.
     * BACAA in Base26 is ZDBA in Base26E
     * <p>
     * This is how excel identifies columns in formulas.
     *
     * @param columnIndex
     * @return
     */
    public static String getReferenceForColumnIndex(int columnIndex) {
        StringBuilder sb = new StringBuilder();

        while (columnIndex >= 0) {
            if (columnIndex == 0) {
                sb.append((char) A_LETTER);
                break;
            }

            char code = (char) (columnIndex % BASE);
            char letter = (char) (code + A_LETTER);
            sb.append(letter);

            columnIndex /= BASE;
            columnIndex -= 1;
        }

        return sb.reverse().toString();
    }
}
