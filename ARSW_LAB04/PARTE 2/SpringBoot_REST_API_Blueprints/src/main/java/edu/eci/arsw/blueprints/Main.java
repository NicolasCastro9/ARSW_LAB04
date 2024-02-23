package edu.eci.arsw.blueprints;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices blueprintsServices = context.getBean(BlueprintsServices.class);
        Point[] points1 = {new Point(10, 10), new Point(20, 20), new Point(30, 30)};
        Point[] points2 = {new Point(30, 13), new Point(22, 24), new Point(10, 35)};
        Blueprint blueprint1 = new Blueprint("author1", "blueprint1", points1);
        Blueprint blueprint2 = new Blueprint("author2", "blueprint2", points2);
        try {
            blueprintsServices.addNewBlueprint(blueprint1);
            blueprintsServices.addNewBlueprint(blueprint2);
        } catch (BlueprintPersistenceException e) {
            System.err.println("Error al registrar el plano: " + e.getMessage());
        }
        try {
            System.out.println("Todos los planos registrados: " + blueprintsServices.getAllBlueprints());
        } catch (BlueprintNotFoundException e) {
            System.err.println("Error al consultar los planos: " + e.getMessage());
        }
        try {
            System.out.println("Plano consultado: " + blueprintsServices.getBlueprint("author1", "blueprint1"));
        } catch (BlueprintNotFoundException e) {
            System.err.println("Error al consultar el plano: " + e.getMessage());
        }
        
    }
}