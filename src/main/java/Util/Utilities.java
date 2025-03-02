package Util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Utilities {
        public static String gerar_email(String nome){//não ta chegando nada
                String[] lista_name = nome.split(" ");
                String email = "";
                try {
                        if (lista_name.length>=2) {
                                email = lista_name[0].concat(".").concat(lista_name[1]);

                        }
                        else{
                                email = lista_name[0];
                        }
                } catch (Exception e) {
                        System.out.println("====================================");
                        System.out.println("Deu ruim " + e);
                }

                email =email.concat("@Instituto_ensino.gov.br");

                return email;




        }
        public static long gerar_id(String  tipo){//revisto
                 long id = switch (tipo) {
                     case "notas" -> ThreadLocalRandom.current().nextLong(100000000, 999999999);//length=9
                     case "matricula" ->
                             ThreadLocalRandom.current().nextLong(10000000, 99999999);//gerar entre intrevalos. essa vai gerar um id de length =8
                     case "professor" -> ThreadLocalRandom.current().nextLong(100000, 999999);//length = 6
                     case "disciplina" ->
                             ThreadLocalRandom.current().nextLong(10000, 99999);//gerar entre intrevalos. essa vai gerar um id de length =5
                     case "turma" -> ThreadLocalRandom.current().nextLong(0, 9999);
                     default -> throw new IllegalArgumentException("Tipo de ID desconhecido: " + tipo);
                 };

            return id;

        }


        public static HashMap<String,Object> reflexao(Object obj){
                HashMap<String,Object> mapa = new HashMap<>();
                Class<?> reflexion = obj.getClass();
                for (Field field : reflexion.getDeclaredFields()) {
                        field.setAccessible(true); // Permite acesso a campos privados
                        try {
                                Object value = field.get(obj); // Obtém o valor do campo para o objeto

                                if(value!=null){
                                        mapa.put(field.getName(),value);
                                }

                        } catch (IllegalAccessException e) {
                                e.printStackTrace();
                        }
                }
                return mapa;
        }

}
