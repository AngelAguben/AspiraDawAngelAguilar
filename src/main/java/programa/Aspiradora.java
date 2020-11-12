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
        final int TOTALDEPENDEN = 5, MINCARGA = 3;
        // Variables necesarias
        String porcentajeCarga, aspiracion, limpiar, dependenciaLimpiada = "";
        // Variables de las dependencias en numero
        double mCoc = 0, mSal = 0, mBan = 0, mHab1 = 0, mHab2 = 0;
        // Variables de las necesarias en numero
        int carga = 0, modAspi, limp;
        // Variable de carga
        final double restaCarga = 1.5; // carga por m2
        // Variables de calculo
        double cargaCoc = 0, cargaSal = 0, cargaBan = 0, cargaHab1 = 0, cargaHab2 = 0;

        // Variables de nombre
        final String COCINA = "cocina", SALON = "salón", BANIO = "banio", HAB1 = "habitación 1", HAB2 = "habitación 2";

        /* CONFIGURAR EL SISTEMA
        La vivienda tiene 1 cocina, 1 salón, 1 cuarto de baño y 2 dormitorios
        De cada depedencia, el sistema pregunta el número de metros de cada 
         */
        do {
            metcoc = JOptionPane.showInputDialog("Metros cuadrados de la " + COCINA);
            mCoc = Double.parseDouble(metcoc);
            // La batería que consume la cocina se guardará en cargaCoc
            cargaCoc = restaCarga * mCoc;
            //System.out.println(cargaCoc);
        } while (mCoc <= 1 || mCoc >= 100);
        do {
            metsal = JOptionPane.showInputDialog("Metros cuadrados del " + SALON);
            mSal = Double.parseDouble(metsal);
            cargaSal = restaCarga * mSal;
        } while (mSal <= 1 || mSal >= 100);
        do {
            metban = JOptionPane.showInputDialog("Metros cuadrados del " + BANIO);
            mBan = Double.parseDouble(metban);
            cargaBan = restaCarga * mBan;
        } while (mBan <= 1 || mBan >= 100);
        do {
            methab1 = JOptionPane.showInputDialog("Metros cuadrados de la " + HAB1);
            mHab1 = Double.parseDouble(methab1);
            cargaHab1 = restaCarga * mHab1;
        } while (mHab1 <= 1 || mHab1 >= 100);
        do {
            methab2 = JOptionPane.showInputDialog("Metros cuadrados de la  " + HAB2);
            mHab2 = Double.parseDouble(methab2);
            cargaHab2 = restaCarga * mHab2;

        } while (mHab2 <= 1 || mHab2 >= 100);

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
        aspiracion = JOptionPane.showInputDialog("Elija un modo de aspiración: \n"
                + "1 - Modo Completo \n"
                + "2 - Modo Dependencias \n");
        modAspi = Integer.parseInt(aspiracion);
        if (carga < MINCARGA) {
            JOptionPane.showMessageDialog(null, "Falta de batría \n"
                    + "Aspiradora regrensando a su base... ");

        } else {
            switch (modAspi) {
                case 1: // Modo Completo 
                    JOptionPane.showMessageDialog(null, "Usted ha elegido el modo completo");
                    for (int i = 0; i < TOTALDEPENDEN; i++) {
                        /*if (carga < MINCARGA) {
                        JOptionPane.showConfirmDialog(null, "Aspiradora regrensando a su base...");
                        break;
                    } else {*/
                        JOptionPane.showMessageDialog(null, "Limpiando...");
                        carga -= restaCarga;
                        //}
                    }
                    break;
                case 2: // Modo Dependencias
                    JOptionPane.showMessageDialog(null, "Usted ha eleigo el modo dependencias");
                    limpiar = JOptionPane.showInputDialog("Elija las dependencias que desea limpiar: \n"
                            + "1 - Cocina \n"
                            + "2 - Salón \n"
                            + "3 - Banio \n"
                            + "4 - Habitación 1 \n"
                            + "5 - Habitación 2 \n"
                            + "6 - Salir \n");
                    limp = Integer.parseInt(limpiar);
                    switch (limp) {
                        case 1: // Cocina
                            JOptionPane.showMessageDialog(null, "Usted ha eleigo el modo dependencias");
                            // Se actualiza la variable de dependenciaLimpia con el 
                            // valor de COCINA
                            dependenciaLimpiada = COCINA;
                            // Se actualiza la variable carga con el valor de lo 
                            // que tenia menos lo que se resta
                            carga -= cargaCoc;
                            break;
                        case 2: // Salón
                            JOptionPane.showMessageDialog(null, "Usted ha eleigo el modo dependencias");
                            // Se actualiza la variable de dependenciaLimpia con el 
                            // valor de SALON
                            dependenciaLimpiada = SALON;
                            // Se actualiza la variable carga con el valor de lo 
                            // que tenia menos lo que se resta
                            carga -= cargaSal;
                            break;
                        case 3: // Banio
                            JOptionPane.showMessageDialog(null, "Usted ha eleigo el modo dependencias");
                            // Se actualiza la variable de dependenciaLimpia con el 
                            // valor de BANIO
                            dependenciaLimpiada = BANIO;
                            // Se actualiza la variable carga con el valor de lo 
                            // que tenia menos lo que se resta
                            carga -= cargaBan;
                            break;
                        case 4: // Habitación 1
                            JOptionPane.showMessageDialog(null, "Usted ha eleigo el modo dependencias");
                            // Se actualiza la variable de dependenciaLimpia con el 
                            // valor de HAB1
                            dependenciaLimpiada = HAB1;
                            // Se actualiza la variable carga con el valor de lo 
                            // que tenia menos lo que se resta
                            carga -= cargaHab1;
                            break;
                        case 5: // HAB2
                            JOptionPane.showMessageDialog(null, "Usted ha eleigo el modo dependencias");
                            // Se actualiza la variable de dependenciaLimpia con el 
                            // valor de HAB2
                            dependenciaLimpiada = HAB2;
                            // Se actualiza la variable carga con el valor de lo 
                            // que tenia menos lo que se resta
                            carga -= cargaHab2;
                            break;
                        case 6: // Salir
                            JOptionPane.showMessageDialog(null, "Usted ha eleigo el modo dependencias");
                            break;
                    }
                    JOptionPane.showMessageDialog(null, "Se ha limpiado la dependencia: " + dependenciaLimpiada);
                    break;
            }
        }
    }
}