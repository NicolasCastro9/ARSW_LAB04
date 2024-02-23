package edu.eci.arsw.blueprints.test.persistence.impl;
import edu.eci.arsw.blueprints.filters.BlueprintsFilters;
import edu.eci.arsw.blueprints.filters.impl.RedundancyFilt;
import edu.eci.arsw.blueprints.filters.impl.SubSampFilt;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FilterTest {

    @Test
    public void shouldRedundancyFiltering() throws BlueprintPersistenceException, BlueprintNotFoundException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bpServices = ac.getBean(BlueprintsServices.class);
        Point[] pts0 = new Point[]{new Point(25, 10), new Point(15, 15), new Point(15, 15), new Point(10, 5)};
        Blueprint bp0 = new Blueprint("Sergio", "Edificio", pts0);
    
        bpServices.addNewBlueprint(bp0);
        bp0 = bpServices.filterBluePrint(bp0);
    
        Point[] ptsCorrects = new Point[]{new Point(25, 10), new Point(15, 15), new Point(10, 5)};
        List<Point> ptsReal= bp0.getPoints();
        try{
            assertEquals(ptsCorrects.length, ptsReal.size());
            for (int i = 0; i < ptsCorrects.length; i++){
                assertEquals(ptsCorrects[i].getX(), ptsReal.get(i).getX());
                assertEquals(ptsCorrects[i].getY(), ptsReal.get(i).getY());
            }
        } catch (Exception e){
            fail(e.getMessage());
        }
    }
    

    @Test
    public void shouldUndersamplingFiltering() throws BlueprintPersistenceException, BlueprintNotFoundException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bpServices = ac.getBean(BlueprintsServices.class);
        Point[] pts0 = new Point[]{new Point(25, 10), new Point(15, 15), new Point(30, 45), new Point(10, 5)};
        Blueprint bp0 = new Blueprint("Sergio", "Edificio", pts0);

        bpServices.addNewBlueprint(bp0);
        bp0 = bpServices.filterBluePrint(bp0);

        Point[] ptsCorrects = new Point[]{new Point(25, 10), new Point(30, 45)};
        List<Point> ptsReal= bp0.getPoints();
        try{
            assertEquals(ptsCorrects.length, ptsReal.size());
            for (int i = 0; i < ptsCorrects.length; i++){
                assertEquals(ptsCorrects[i].getX(), ptsReal.get(i).getX());
                assertEquals(ptsCorrects[i].getY(), ptsReal.get(i).getY());
            }
        } catch (Exception e){
            fail(e.getMessage());
        }
    }

}