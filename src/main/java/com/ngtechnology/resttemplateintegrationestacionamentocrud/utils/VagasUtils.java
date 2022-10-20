package com.ngtechnology.resttemplateintegrationestacionamentocrud.utils;

import org.springframework.util.ObjectUtils;

public class VagasUtils {
    public static void validatedHeader(String partner)throws Exception {
        validatedPartner(partner);

    }
    public static Boolean validatedPartner (String partner) throws Exception {
        if (!ObjectUtils.isEmpty(partner))
            if (partner.equals("Star-Park")|| partner.equals("Center-Park")|| partner.equals("Downton-Park")){
                return true;
            }
        throw new Exception("Partner inválido");
    }
}
