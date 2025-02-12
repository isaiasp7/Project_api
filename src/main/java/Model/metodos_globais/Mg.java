package Model.metodos_globais;

public class Mg {
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

}
