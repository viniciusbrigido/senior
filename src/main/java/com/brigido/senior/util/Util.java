package com.brigido.senior.util;

import br.com.caelum.stella.validation.CPFValidator;

public class Util {

    public static boolean isCpfValid(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        try {
            cpfValidator.assertValid(cpf);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String normalizeCpf(String cpf) {
        return cpf.replaceAll("\\D+", "");
    }
}
