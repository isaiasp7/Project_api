package Util;

import java.util.concurrent.ThreadLocalRandom;

public class utilities {
        public static String gerar_email(String nome){
                String[] lista_name = nome.split(" ");
                String email = "";
                try {
                        if (lista_name.length>2) {
                                email = lista_name[0].concat(".").concat(lista_name[2]);

                        }
                        else{
                                email = lista_name[0].concat(".").concat(lista_name[1]);
                        }
                } catch (Exception e) {
                        System.out.println("Deu ruim " + e);
                }
                return email.concat("@Instituto_ensino.gov.br");




        }
        public static long gerar_id(String  tipo){
                long id=0;
                switch(tipo){


                        case "notas":
                                id= ThreadLocalRandom.current().nextLong(000000000,999999999)+10*100;//length=9
                                break;
                        case "matricula":
                                id =  ThreadLocalRandom.current().nextInt(00000000, 99999999);//gerar entre intrevalos. essa vai gerar um id de length =8
                                break;

                        case "professor":
                                id= ThreadLocalRandom.current().nextInt(000000, 999999);//length = 6
                                break;

                        case "disciplina":
                                id= ThreadLocalRandom.current().nextInt(00000, 99999);//gerar entre intrevalos. essa vai gerar um id de length =5
                                break;

                        case "turma":
                                id= ThreadLocalRandom.current().nextInt(0000, 9999);
                                break;
                }

                return id;

        }

}
