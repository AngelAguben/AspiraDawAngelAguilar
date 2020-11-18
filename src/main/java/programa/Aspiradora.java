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
        // VARIABLES
        // Varibales de autenticación del usuario
        final String USUARIO = "admin", PASSWORD = "admin";
        String user = "", passwd = "";
        // Variable de dependencias totales y de minima y máxima carga
        final int TOTALDEPENDEN = 5, MINCARGA = 3, MAXCARGA = 100;
        // Variables necesarias para la ejecución del programa de tipo texto
        String porcentajeCarga, aspiracion, limpiar, fregar, dependenciaLimpiada = "",
                fregado = "", menu = "";
        // Variable de la carga
        double carga = 0;
        // Variables necesarias de tipo número
        int menuNum = 0, modAspi = 0, limp = 0, modFreg = 0, freg = 0;
        // Variables de resta carga por m2 de limpieza y aspiración y fregado
        final double RESTACARGALIMP = 1.5, RESTACARGAFREG = 2.25;
        // Variables boolean 
        boolean tieneCarga = true;

        // ARRAYS
        // Array para el nombre de las dependencias
        String[] dependencia = {"cocina", "salon", "banio", "habitación 1", "habitacion 2"};

        // Array para los metros cuadrados de las dependencias
        int[] metros = new int[TOTALDEPENDEN];
        // Variables para los arrays
        String textoMetros = "";

        // Boolean para saber si están limpiadas las habitaciones
        boolean[] limpiada = new boolean[TOTALDEPENDEN];

        /* CONFIGURAR EL SISTEMA
        La vivienda tiene 1 cocina, 1 salón, 1 cuarto de baño y 2 dormitorios
        De cada depedencia, el sistema pregunta el número de metros de cada 
         */
        // RESOLUCIÓN DEL PROGRAMA
        // Introduzción de datos
        // Autenticación del usuario
        do {
            user = JOptionPane.showInputDialog("Introduzca el usuario");
            passwd = JOptionPane.showInputDialog("Introduzca la contraseña");
            // Si el usuario no introduce el usuario y la contraseña correctamente
            // no seguirá el programa
        } while (!USUARIO.equalsIgnoreCase(user) || !PASSWORD.equalsIgnoreCase(passwd));

        // Se ejecuitará mientras i sea menor que las dependencias totales
        for (int i = 0; i < TOTALDEPENDEN; i++) {
            do {
                textoMetros = JOptionPane.showInputDialog("Introduzca los metros "
                        + "cuadrados de la dependencia " + dependencia[i]);
                metros[i] = Integer.parseInt(textoMetros);
                // Se repetirá si el usuario pone un valor inferior a 0 ó superior a 100
            } while (metros[i] < 0 && metros[i] > 100);
        }

        // CARGA
        // Establece el nivel de batería. (entre 0% y 100%).
        do {
            porcentajeCarga = JOptionPane.showInputDialog("Carga de la aspiradora (0-100) ");
            carga = Integer.parseInt(porcentajeCarga);
            // Se repetirá si la carga es inferior a 0 ó superior a 100
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
        // Implementamos el array de texto para 
        // que el usuario elija un modo
        do {
            menu = JOptionPane.showInputDialog("Elija un modo de fregado: \n"
                    + "1 - Aspiración \n"
                    + "2 - Aspiración y fregado \n"
                    + "3 - Estado general\n"
                    + "4 - Base de carga \n"
                    + "5 - Salir \n");
            // Pasará el texto de munu a int de menuNum
            menuNum = Integer.parseInt(menu);

            // Si carga es menor a MINCARGA
            if (carga < MINCARGA) {
                // Mostrará este mensaje y terminará el programa
                JOptionPane.showMessageDialog(null, "Falta de batría \n"
                        + "Aspiradora regrensando a su base... ");
            } else { // Si no ejecutará el siguiente código

                switch (menuNum) {
                    case 1: // Aspiración
                        do {
                            // El usuario eligirá el modo de aspiracioón
                            aspiracion = JOptionPane.showInputDialog("Elija un modo de aspiración: \n"
                                    + "1 - Modo Completo \n"
                                    + "2 - Modo Dependencias \n"
                                    + "3 - Salir \n");
                            // Pasará el texto de aspiración a int de modAspi
                            modAspi = Integer.parseInt(aspiracion);

                            switch (modAspi) { // Modo aspiración
                                case 1: // Modo Completo de limpiar
                                    JOptionPane.showMessageDialog(null, "Usted ha elegido el modo completo \n"
                                            + "Batería = " + carga + " \n");

                                    // Se ejecutará hasta que i sea mayor al número de dependencias
                                    for (int i = 0; i < TOTALDEPENDEN; i++) {
                                        // Si carga es mayor a la posición del array i 
                                        // en metros más la craga mínima que es 3, 
                                        // ejecutará el código de if
                                        if (carga > (metros[i] + MINCARGA)) {
                                            // Mostrará el siguiente mensaje 
                                            JOptionPane.showMessageDialog(null, "Limpiando "
                                                    + "la dependencia " + dependencia[i] + " ...");
                                            limpiada[i] = true; // Posición de i en el 
                                            // array limpiada será true

                                            // Actualizará la variable de carga al valor 
                                            // adecuado, multiplicando los metros de la 
                                            // posición de i, multiplicandolo por lo que
                                            // consume cada metro de batería menos la 
                                            // carga de la variable que tenga carga
                                            carga -= metros[i] * RESTACARGALIMP;
                                            // System.out.println(carga);
                                        } else {
                                            // De lo contrario, mostrará este mensaje
                                            JOptionPane.showMessageDialog(null, "Falta de "
                                                    + "batería para la dependencia " + dependencia[i]);
                                            limpiada[i] = false; // Y la posición de i 
                                            // en el array limpiada será false 
                                        }
                                    }
                                    // Crearé un for para que me muestre las habitaciones 
                                    // que ha limpiado hasta que i sea mayor a TOTALDEPENDEN
                                    for (int i = 0; i < TOTALDEPENDEN; i++) {
                                        if (limpiada[i] == true) {
                                            // Mostrará las dependencias limpiadas una por una
                                            JOptionPane.showMessageDialog(null, "Se han "
                                                    + "limpiado las dependencias: " + dependencia[i]);
                                        } else { // Mostrará las dependencias no limpiadas
                                            JOptionPane.showMessageDialog(null, "No se han "
                                                    + "limpiado las dependencias: " + dependencia[i]);
                                        }
                                    }
                                    break;
                                case 2: // Modo Dependencias de limpiar
                                    JOptionPane.showMessageDialog(null, "Usted ha eleigo el modo "
                                            + "dependencias");
                                    do {
                                        do {
                                            // Implementamos el array de texto para 
                                            // que el usuario elija un modo
                                            limpiar = JOptionPane.showInputDialog("Elija las dependencias "
                                                    + "que desea limpiar: \n"
                                                    + "Batería = " + carga + " \n"
                                                    + "1 - " + dependencia[0] + " \n"// Cocina
                                                    + "2 - " + dependencia[1] + " \n"// Salon
                                                    + "3 - " + dependencia[2] + " \n"// Banio
                                                    + "4 - " + dependencia[3] + " \n"// Hab1
                                                    + "5 - " + dependencia[4] + " \n"// Hab 2
                                                    + "6 - Salir"); // Salir
                                            // limp es el int convertido de limpiar
                                            limp = Integer.parseInt(limpiar);
                                        } while (limp > 6); //Repite hasta que limp 
                                        // sea mayor a 6

                                        // Opción de limpiar
                                        switch (limp) {
                                            case 1: // Cocina
                                                if (carga > (metros[0] + MINCARGA)) {
                                                    // Se actualiza la variable de dependenciaLimpia con el 
                                                    // valor de COCINA
                                                    dependenciaLimpiada = dependencia[0];
                                                    // Se actualiza la variable carga con el valor de lo 
                                                    // que tenia menos lo que se resta
                                                    carga -= RESTACARGALIMP * metros[0];
                                                } else {
                                                    // Si no, mostrará este mensaje
                                                    JOptionPane.showMessageDialog(null, "La batería "
                                                            + "no es suficiente");
                                                    tieneCarga = false; // Variable de tieneCarga es false
                                                }
                                                break;
                                            case 2: // Salón
                                                if (carga > (metros[1] + MINCARGA)) {
                                                    // Se actualiza la variable de dependenciaLimpia con el 
                                                    // valor de SALON
                                                    dependenciaLimpiada = dependencia[1];
                                                    // Se actualiza la variable carga con el valor de lo 
                                                    // que tenia menos lo que se resta
                                                    carga -= RESTACARGALIMP * metros[1];
                                                } else {
                                                    // Si no, mostrará este mensaje
                                                    JOptionPane.showMessageDialog(null, "La batería "
                                                            + "no es suficiente");
                                                    // Y la variable tieneCarga será false
                                                    tieneCarga = false;
                                                }
                                                break;
                                            case 3: // Banio
                                                if (carga > (metros[2] + MINCARGA)) {
                                                    // Se actualiza la variable de dependenciaLimpia con el 
                                                    // valor de BANIO
                                                    dependenciaLimpiada = dependencia[2];
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
                                                    dependenciaLimpiada = dependencia[3];
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
                                                    dependenciaLimpiada = dependencia[4];
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
                        } while (modAspi != 3); // Repite hasta que el usuario escriba un 3
                        break;

                    case 2: // Aspiración y fregado
                        do {
                            // Implementamos el array de texto para 
                            // que el usuario elija un modo
                            fregado = JOptionPane.showInputDialog("Elija un modo de fregado: \n"
                                    + "1 - Modo Completo \n"
                                    + "2 - Modo Dependencias \n"
                                    + "3 - Salir \n");
                            modFreg = Integer.parseInt(fregado);
                            switch (modFreg) {
                                case 1: // Modo Completo
                                    JOptionPane.showMessageDialog(null, "Usted ha elegido el modo completo \n"
                                            + "Batería = " + carga + " \n");

                                    // Se ejecutará hasta que i sea mayor al número de dependencias
                                    for (int i = 0; i < TOTALDEPENDEN; i++) {
                                        // Si carga es mayor a la posición del array i 
                                        // en metros más la craga mínima que es 3, 
                                        // ejecutará el código de if
                                        if (carga > (metros[i] + MINCARGA)) {
                                            // Mostrará el siguiente mensaje 
                                            JOptionPane.showMessageDialog(null, "Limpiando "
                                                    + "la dependencia " + dependencia[i] + " ...");
                                            limpiada[i] = true; // Posición de i en el 
                                            // array limpiada será true

                                            // Actualizará la variable de carga al valor 
                                            // adecuado, multiplicando los metros de la 
                                            // posición de i, multiplicandolo por lo que
                                            // consume cada metro de batería menos la 
                                            // carga de la variable que tenga carga
                                            carga -= metros[i] * RESTACARGAFREG;
                                            // System.out.println(carga);
                                        } else {
                                            // De lo contrario, mostrará este mensaje
                                            JOptionPane.showMessageDialog(null, "Falta de "
                                                    + "batería para la dependencia " + dependencia[i]);
                                            limpiada[i] = false; // Y la posición de i 
                                            // en el array limpiada será false 
                                        }
                                    }
                                    // Crearé un for para que me muestre las habitaciones 
                                    // que ha limpiado hasta que i sea mayor a TOTALDEPENDEN
                                    for (int i = 0; i < TOTALDEPENDEN; i++) {
                                        if (limpiada[i] == true) {
                                            // Mostrará las dependencias limpiadas una por una
                                            JOptionPane.showMessageDialog(null, "Se han "
                                                    + "limpiado las dependencias: " + dependencia[i]);
                                        } else { // Mostrará las dependencias no limpiadas
                                            JOptionPane.showMessageDialog(null, "No se han "
                                                    + "limpiado las dependencias: " + dependencia[i]);
                                        }
                                    }
                                    break;

                                case 2: // Modo Dependencias de fregado
                                    JOptionPane.showMessageDialog(null, "Usted ha eleigo el modo "
                                            + "dependencias");
                                    do {
                                        do {
                                            // Implementamos el array de texto para 
                                            // que el usuario elija un modo
                                            fregar = JOptionPane.showInputDialog("Elija las dependencias "
                                                    + "que desea aspirar y fregar: \n"
                                                    + "Batería = " + carga + " \n"
                                                    + "1 - " + dependencia[0] + " \n"// Cocina
                                                    + "2 - " + dependencia[1] + " \n"// Salon
                                                    + "3 - " + dependencia[2] + " \n"// Banio
                                                    + "4 - " + dependencia[3] + " \n"// Hab1
                                                    + "5 - " + dependencia[4] + " \n"// Hab 2
                                                    + "6 - Salir"); // Salir
                                            // freg es el valor númerico del String fregar
                                            freg = Integer.parseInt(fregar);
                                        } while (freg > 6); // Repite mientras freg 
                                        // sea menor a 6

                                        // Opción de limpiar
                                        switch (freg) {
                                            case 1: // Cocina
                                                // Si carga es mayor a posición de metros
                                                // por la cantidad de batería que consume
                                                // por metros cuadrados más la carga 
                                                // mínima que necesita la aspiradora
                                                if (carga > ((metros[0] * RESTACARGAFREG) + MINCARGA)) {
                                                    // Se actualiza la variable de dependenciaLimpia con el 
                                                    // valor de COCINA
                                                    dependenciaLimpiada = dependencia[0];
                                                    // Se actualiza la variable carga con el valor de lo 
                                                    // que tenia menos lo que se resta
                                                    carga -= RESTACARGAFREG * metros[0];
                                                } else {
                                                    // Si no mostrará este mensaje
                                                    JOptionPane.showMessageDialog(null, "La batería "
                                                            + "no es suficiente");
                                                    tieneCarga = false; // Variable de tieneCarga es false
                                                }
                                                break;
                                            case 2: // Salón
                                                if (carga > ((metros[1] * RESTACARGAFREG) + MINCARGA)) {
                                                    // Se actualiza la variable de dependenciaLimpia con el 
                                                    // valor de SALON
                                                    dependenciaLimpiada = dependencia[1];
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
                                                if (carga > ((metros[2] * RESTACARGAFREG) + MINCARGA)) {
                                                    // Se actualiza la variable de dependenciaLimpia con el 
                                                    // valor de BANIO
                                                    dependenciaLimpiada = dependencia[2];
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
                                                if (carga > ((metros[3] * RESTACARGAFREG) + MINCARGA)) {
                                                    // Se actualiza la variable de dependenciaLimpia con el 
                                                    // valor de HAB1
                                                    dependenciaLimpiada = dependencia[3];
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
                                                if (carga > ((metros[4] * RESTACARGAFREG) + MINCARGA)) {
                                                    // Se actualiza la variable de dependenciaLimpia con el 
                                                    // valor de HAB2
                                                    dependenciaLimpiada = dependencia[4];
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

                        } while (modFreg != 3); // Repite hasta que el usuario escriba un 3
                    case 3: // Muestra el estado general
                        JOptionPane.showMessageDialog(null, "Fecha: \n"
                                + "Nivel de batería: " + carga + "%\n"
                                + "Lugar donde está parado " + "\n"
                                + "Dependencias y metros de la casa: \n"
                                + " 1 - " + dependencia[0] + " - " + metros[0] + " metros cuadrados \n"
                                + " 2 - " + dependencia[1] + " - " + metros[1] + " metros cuadrados \n"
                                + " 3 - " + dependencia[2] + " - " + metros[2] + " metros cuadrados \n"
                                + " 4 - " + dependencia[3] + " - " + metros[3] + " metros cuadrados \n"
                                + " 5 - " + dependencia[4] + " - " + metros[4] + " metros cuadrados");

                        break;
                    case 4: // Base de carga
                        JOptionPane.showMessageDialog(null, "Aspiradora llegando a su base de carga...");
                        JOptionPane.showMessageDialog(null, "Aspiradora entrando en modo de carga...");
                        // El valor que haya en la variable carga lo sustituye 
                        // por un 100
                        carga = MAXCARGA;
                        JOptionPane.showMessageDialog(null, "Robot cargado al " + carga + "%");
                        break;
                    case 5: // Finaliza el programa
                        JOptionPane.showMessageDialog(null, "Usted ha salido del programa");
                        break;
                }
            }
        } while (menuNum != 5);
    }
}
