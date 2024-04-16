/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;

/** Tutorial 2 resources as property values */
public class Tutorial02 extends Object {

    public static void main(String args[]) {
        // some definitions
        String personURI = "http://somewhere/JohnSmith";
        String givenName = "John";
        String familyName = "Smith";
        String fullName = givenName + " " + familyName;

        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        // create the resource
        // and add the properties cascading style
        Resource johnSmith = model.createResource(personURI)
                .addProperty(VCARD.FN, fullName)
                .addProperty(VCARD.N, model.createResource()
                        .addProperty(VCARD.Given, givenName)
                        .addProperty(VCARD.Family, familyName));

        // Imprimir el modelo en la consola
        System.out.println("Modelo RDF generado:");
        model.write(System.out);

        // Guardar el modelo en un archivo RDF
        try {
            FileOutputStream output = new FileOutputStream("modelo_tutorial02.rdf");
            model.write(output, "RDF/XML");
            output.close();
            System.out.println("\nModelo RDF guardado en 'modelo_tutorial02.rdf'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
