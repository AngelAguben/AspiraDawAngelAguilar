/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// api java time (8) para la hora
package programa;

import javax.swing.JOptionPane;

/**
 *
 * @author Ángel
 */
public class Aspiradora {

    public static void main(String[] args) {
        // Variables
        // Variables de las dependencias
        String metcoc, metsal, metban, methab1, methab2;
        // Variable de dependencias totales y de minima carga
        final int TOTALDEPENDEN = 4, MINCARGA = 3;
        // Variables necesarias
        String porcentajeCarga, aspiracion, limpiar, fregar, dependenciaLimpiada = "",
                fregado = "", menu = "";
        // Variables de las dependencias en numero
        double carga = 0;
        // Variables de las necesarias en numero
        int menuNum = 0, modAspi = 0, limp = 0, modFreg = 0, freg = 0;
        // Variables de resta carga
        final double RESTACARGALIMP = 1.5, RESTACARGAFREG = 2.25; // carga por m2
        // Variables de calculo
        boolean tieneCarga = true;

        // Variables de nombre
        final String COCINA = "cocina", SALON = "salón", BANIO = "banio",
                HAB1 = "habitación 1", HAB2 = "habitación 2";

        // Variables para los arrays
        int numDepen = 5;

        String numeroDepen = "", textoMetros = "";


        /* CONFIGURAR EL SISTEMA
        La vivienda tiene 1 cocina, 1 salón, 1 cuarto de baño y 2 dormitorios
        De cada depedencia, el sistema pregunta el número de metros de cada 
         */
        // RESOLUCIÓN EL PROGRAMA
        // Introduzción de datos
        /*do {
            numeroDepen = JOptionPane.showInputDialog("¿Cuántas dependencias tiene?");
            numDepen = Integer.parseInt(numeroDepen);

        } while (numDepen < 0 || numDepen > 10); // Valor entre 1 y 10
        // Variable de nombre con array
         */
        String[] dependencia = {"cocina", "salon", "banio", "habitación 1", "habitacion 2"};

        int[] metros = new int[numDepen]; // METROS

        // Número de dependencias de la casa
        /*
        for (int i = 0; i < numDepen; i++) {
            dependencia[i] = JOptionPane.showInputDialog("Introduzca el nombre de la "
                    + "dependencia " + (i + 1));
        }
         */
        // Array para los metros cuadrados de las dependencias
        for (int i = 0; i < numDepen; i++) {
            do {

                textoMetros = JOptionPane.showInputDialog("Introduzca los metros "
                        + "cuadrados de la dependencia " + dependencia[i]);
                metros[i] = Integer.parseInt(textoMetros);

            } while (metros[i] < 0 && metros[i] > 100);
        }

        // CARGA
        // Establece el nivel de batería. (entre 0% y 100%).
        do {
            porcentajeCarga = JOptionPane.showInputDialog("Carga de la aspiradora (0-100) ");
            carga = Integer.parseInt(porcentajeCarga);
        } while (carga < 0 || carga > 100);


        /* ASPIRACIÓN
        
        Modo completo. 
        En este modo, la aspiradora limpia el piso entero. 
        El robot va limpiando habitaciones en función de su batería. Si al 
        entrar en una habitación no le llega la batería para poder 
        completarla entonces la aspiradora se para e informa al usuario que 
        no puede terminar y también informa de las dependencias que ha 
        podido limpiar.
        
        Modo dependencias. 
        Sólo limpia las habitaciones que se le indiquen, 
        si tiene batería.
        
        En ambos modos, cada metro cuadrado de limpieza agota un 1,5% de 
        batería. Cada vez que se limpia una habitación se actualiza el estado 
        de la batería, para controlar si puede limpiar la siguiente habitación
         */
        menu = JOptionPane.showInputDialog("Elija un modo de fregado: \n"
                + "1 - Aspiración \n"
                + "2 - Aspiración y fregado \n"
                + "3 - Estado general\n"
                + "4 - Base de carga \n"
                + "5 - Salir \n");

        menuNum = Integer.parseInt(menu);
        if (carga < MINCARGA) {
            JOptionPane.showMessageDialog(null, "Falta de batría \n"
                    + "Aspiradora regrensando a su base... ");

        } else {

            switch (menuNum) {
                case 1: // Aspiración
                    aspiracion = JOptionPane.showInputDialog("Elija un modo de aspiración: \n"
                            + "1 - Modo Completo \n"
                            + "2 - Modo Dependencias \n");
                    modAspi = Integer.parseInt(aspiracion);

                    switch (modAspi) {
                        case 1: // Modo Completo de limpiar
                            JOptionPane.showMessageDialog(null, "Usted ha elegido el modo completo");
                            for (int i = 0; i < TOTALDEPENDEN; i++) {
                                /*if (carga < MINCARGA) {
                        JOptionPane.showConfirmDialog(null, "Aspiradora regrensando a su base...");
                        break;
                    } else {*/
                                JOptionPane.showMessageDialog(null, "Limpiando...");
                                carga -= RESTACARGALIMP;
                                //}
                            }
                            break;
                        case 2: // Modo Dependencias de limpiar
                            JOptionPane.showMessageDialog(null, "Usted ha eleigo el modo "
                                    + "dependencias");
                            do {
                                do {
                                    // Implementamos el array de texto
                                    limpiar = JOptionPane.showInputDialog("Elija las dependencias "
                                            + "que desea limpiar: \n"
                                            + "Batería = " + carga + " \n"
                                            + "1 - " + dependencia[0] + " \n"// Cocina
                                            + "2 - " + dependencia[1] + " \n"// Salon
                                            + "3 - " + dependencia[2] + " \n"// Banio
                                            + "4 - " + dependencia[3] + " \n"// Hab1
                                            + "5 - " + dependencia[4] + " \n"// Hab 2
                                            + "6 - Salir"); // Salir
                                    limp = Integer.parseInt(limpiar);
                                } while (limp > 6);

                                // Opción de limpiar
                                switch (limp) {
                                    case 1: // Cocina
                                        if (carga > (metros[0] + MINCARGA)) {
                                            // Se actualiza la variable de dependenciaLimpia con el 
                                            // valor de COCINA
                                            dependenciaLimpiada = COCINA;
                                            // Se actualiza la variable carga con el valor de lo 
                                            // que tenia menos lo que se resta
                                            carga -= RESTACARGALIMP * metros[0];
                                        } else {
                                            JOptionPane.showMessageDialog(null, "La batería "
                                                    + "no es suficiente");
                                            tieneCarga = false; // Variable de tieneCarga es false
                                        }
                                        break;
                                    case 2: // Salón
                                        if (carga > (metros[1] + MINCARGA)) {
                                            // Se actualiza la variable de dependenciaLimpia con el 
                                            // valor de SALON
                                            dependenciaLimpiada = SALON;
                                            // Se actualiza la variable carga con el valor de lo 
                                            // que tenia menos lo que se resta
                                            carga -= RESTACARGALIMP * metros[1];
                                        } else {
                                            JOptionPane.showMessageDialog(null, "La batería "
                                                    + "no es suficiente");
                                            tieneCarga = false;
                                        }
                                        break;
                                    case 3: // Banio
                                        if (carga > (metros[2] + MINCARGA)) {
                                            // Se actualiza la variable de dependenciaLimpia con el 
                                            // valor de BANIO
                                            dependenciaLimpiada = BANIO;
                                            // Se actualiza la variable carga con el valor de lo 
                                            // que tenia menos lo que se resta
                                            carga -= RESTACARGALIMP * metros[2];
                                        } else {
                                            JOptionPane.showMessageDialog(null, "La batería "
                                                    + "no es suficiente");
                                            tieneCarga = false;
                                        }
                                        break;
                                    case 4: // Habitación 1
                                        if (carga > (metros[3] + MINCARGA)) {
                                            // Se actualiza la variable de dependenciaLimpia con el 
                                            // valor de HAB1
                                            dependenciaLimpiada = HAB1;
                                            // Se actualiza la variable carga con el valor de lo 
                                            // que tenia menos lo que se resta
                                            carga -= RESTACARGALIMP * metros[3];
                                        } else {
                                            JOptionPane.showMessageDialog(null, "La batería "
                                                    + "no es suficiente");
                                            tieneCarga = false;
                                        }
                                        break;
                                    case 5: // Habitación 2
                                        if (carga > (metros[4] + MINCARGA)) {
                                            // Se actualiza la variable de dependenciaLimpia con el 
                                            // valor de HAB2
                                            dependenciaLimpiada = HAB2;
                                            // Se actualiza la variable carga con el valor de lo 
                                            // que tenia menos lo que se resta
                                            carga -= RESTACARGALIMP * metros[4];
                                        } else {
                                            JOptionPane.showMessageDialog(null, "La batería "
                                                    + "no es suficiente");
                                            tieneCarga = false;
                                        }
                                        break;
                                    case 6: // Salir
                                        JOptionPane.showMessageDialog(null, "Usted ha "
                                                + "salido del programa");
                                        break;
                                }
                                if (limp != 6 && tieneCarga == true) {
                                    JOptionPane.showMessageDialog(null, "Limpiando...");
                                    JOptionPane.showMessageDialog(null, "Se ha limpiado la "
                                            + "dependencia: " + dependenciaLimpiada);
                                }
                                // Repite mientas limp no sea 6
                            } while (limp != 6);
                    }
                    break;

                case 2: // Aspiración y fregado
                    fregado = JOptionPane.showInputDialog("Elija un modo de fregado: \n"
                            + "1 - Modo Completo \n"
                            + "2 - Modo Dependencias \n");
                    modFreg = Integer.parseInt(fregado);
                    switch (modFreg) {
                        case 1: // Modo Completo

                        case 2: // Modo Dependencias de fregado
                            JOptionPane.showMessageDialog(null, "Usted ha eleigo el modo "
                                    + "dependencias");
                            do {
                                do {
                                    // Implementamos el array de texto
                                    fregar = JOptionPane.showInputDialog("Elija las dependencias "
                                            + "que desea aspirar y fregar: \n"
                                            + "Batería = " + carga + " \n"
                                            + "1 - " + dependencia[0] + " \n"// Cocina
                                            + "2 - " + dependencia[1] + " \n"// Salon
                                            + "3 - " + dependencia[2] + " \n"// Banio
                                            + "4 - " + dependencia[3] + " \n"// Hab1
                                            + "5 - " + dependencia[4] + " \n"// Hab 2
                                            + "6 - Salir"); // Salir
                                    freg = Integer.parseInt(fregar);
                                } while (freg > 6);

                                // Opción de limpiar
                                switch (freg) {
                                    case 1: // Cocina
                                        if (carga > (metros[0] + MINCARGA)) {
                                            // Se actualiza la variable de dependenciaLimpia con el 
                                            // valor de COCINA
                                            dependenciaLimpiada = COCINA;
                                            // Se actualiza la variable carga con el valor de lo 
                                            // que tenia menos lo que se resta
                                            carga -= RESTACARGAFREG * metros[0];
                                        } else {
                                            JOptionPane.showMessageDialog(null, "La batería "
                                                    + "no es suficiente");
                                            tieneCarga = false; // Variable de tieneCarga es false
                                        }
                                        break;
                                    case 2: // Salón
                                        if (carga > (metros[1] + MINCARGA)) {
                                            // Se actualiza la variable de dependenciaLimpia con el 
                                            // valor de SALON
                                            dependenciaLimpiada = SALON;
                                            // Se actualiza la variable carga con el valor de lo 
                                            // que tenia menos lo que se resta
                                            carga -= RESTACARGAFREG * metros[1];
                                        } else {
                                            JOptionPane.showMessageDialog(null, "La batería "
                                                    + "no es suficiente");
                                            tieneCarga = false;
                                        }
                                        break;
                                    case 3: // Banio
                                        if (carga > (metros[2] + MINCARGA)) {
                                            // Se actualiza la variable de dependenciaLimpia con el 
                                            // valor de BANIO
                                            dependenciaLimpiada = BANIO;
                                            // Se actualiza la variable carga con el valor de lo 
                                            // que tenia menos lo que se resta
                                            carga -= RESTACARGAFREG * metros[2];
                                        } else {
                                            JOptionPane.showMessageDialog(null, "La batería "
                                                    + "no es suficiente");
                                            tieneCarga = false;
                                        }
                                        break;
                                    case 4: // Habitación 1
                                        if (carga > (metros[3] + MINCARGA)) {
                                            // Se actualiza la variable de dependenciaLimpia con el 
                                            // valor de HAB1
                                            dependenciaLimpiada = HAB1;
                                            // Se actualiza la variable carga con el valor de lo 
                                            // que tenia menos lo que se resta
                                            carga -= RESTACARGAFREG * metros[3];
                                        } else {
                                            JOptionPane.showMessageDialog(null, "La batería "
                                                    + "no es suficiente");
                                            tieneCarga = false;
                                        }
                                        break;
                                    case 5: // Habitación 2
                                        if (carga > (metros[4] + MINCARGA)) {
                                            // Se actualiza la variable de dependenciaLimpia con el 
                                            // valor de HAB2
                                            dependenciaLimpiada = HAB2;
                                            // Se actualiza la variable carga con el valor de lo 
                                            // que tenia menos lo que se resta
                                            carga -= RESTACARGAFREG * metros[4];
                                        } else {
                                            JOptionPane.showMessageDialog(null, "La batería "
                                                    + "no es suficiente");
                                            tieneCarga = false;
                                        }
                                        break;
                                    case 6: // Salir
                                        JOptionPane.showMessageDialog(null, "Usted ha "
                                                + "salido del programa");
                                        break;
                                }
                                if (freg != 6 && tieneCarga == true) {
                                    JOptionPane.showMessageDialog(null, "Limpiando...");
                                    JOptionPane.showMessageDialog(null, "Se ha limpiado la "
                                            + "dependencia: " + dependenciaLimpiada);
                                }
                                // Repite mientas limp no sea 6
                            } while (freg != 6);
                    }
                    break;
            }
        }
    }
}
