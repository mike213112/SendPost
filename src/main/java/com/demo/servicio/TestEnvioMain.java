/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.servicio;

import com.demo.bean.Persona;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mmendez
 */
public class TestEnvioMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // enviar persona .... a docker en el puerto 8080

        Persona response = new Persona();
        try {
            Client client;
            client = Client.create();
            client.setConnectTimeout(10000);
            client.setReadTimeout(60000);

            WebResource service = client
                    .resource("http://172.17.0.2:8080/ServicioWeb/rest/enviarDatos");

            Persona request = new Persona();
            request.setId(1);
            request.setNombre("Manuel");
            request.setEdad(31);

            response = service
                    .type(MediaType.APPLICATION_JSON)
                    .post(Persona.class, request);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (response != null) {
            System.out.println("id:" + response.getId());
            System.out.println("Nombre:" + response.getNombre());
            System.out.println("edad:" + response.getEdad());
        }

    }

}
