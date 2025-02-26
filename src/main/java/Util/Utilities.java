package Util;

import java.util.concurrent.ThreadLocalRandom;

public class Utilities {
        public static String gerar_email(String nome){
                String[] lista_name = nome.split(" ");
                String email = "";
                try {
                        if (lista_name.length>=2) {
                                email = lista_name[0].concat(".").concat(lista_name[1]);

                        }
                        else{
                                email = lista_name[0].concat(".");
                        }
                } catch (Exception e) {
                        System.out.println("====================================");
                        System.out.println("Deu ruim " + e);
                }

                email =email.concat("@Instituto_ensino.gov.br");

                return email;




        }
        public static long gerar_id(String  tipo){
                 long id ;
                switch(tipo){

                        case "notas":
                                id= ThreadLocalRandom.current().nextLong(100000000,999999999);//length=9
                                break;
                        case "matricula":
                                id =  ThreadLocalRandom.current().nextLong(10000000, 99999999);//gerar entre intrevalos. essa vai gerar um id de length =8
                                break;

                        case "professor":
                                id= ThreadLocalRandom.current().nextLong(100000, 999999);//length = 6
                                break;

                        case "disciplina":
                                id= ThreadLocalRandom.current().nextLong(10000, 99999);//gerar entre intrevalos. essa vai gerar um id de length =5
                                break;

                        case "turma":
                                id= ThreadLocalRandom.current().nextLong(0, 9999);
                                break;
                        default:
                                throw new IllegalArgumentException("Tipo de ID desconhecido: " + tipo);



                }

                return id;

        }

}
