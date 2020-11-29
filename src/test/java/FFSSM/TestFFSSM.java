/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FFSSM;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author bapti
 */
public class TestFFSSM {
    Club Corseplongee;
    Site Ajaccio, Bastia;
    Moniteur moniteurMichel;
    Plongee Aja, Bas;
    Plongeur baptiste, jules;
    Licence licenceBaptiste, licenceJules;
    
    
    @BeforeEach
    public void setUp(){
        Corseplongee = new Club(moniteurMichel, "Corseplongee", "0606060606");
        Ajaccio = new Site("Ajaccio", "Découvrir les fonds marins et des poissons magnifiques");
        Bastia = new Site("Bastia", "Découvrir ses fonds marins incroyables");
        moniteurMichel = new Moniteur("1000", "Bridou", "Michel", "20 rue de la montee", "0666666666", LocalDate.of(1988, 8, 12), 5, 2345);
        Aja = new Plongee(Ajaccio, moniteurMichel, LocalDate.of(2020, 11, 29), 10, 45);
        Bas = new Plongee(Bastia, moniteurMichel, LocalDate.of(2020, 11, 30), 15, 60);
        baptiste = new Plongeur("5432", "Villedieu", "Baptiste", "30 rue du milieu", "0660066012", LocalDate.of(2000, 11, 15), 2);
        jules = new Plongeur("8765", "Claudel", "Jules", "8 rue de la profondeur", "0770771712", LocalDate.of(2001, 06, 25), 3);
        licenceBaptiste = new Licence(baptiste, "123", LocalDate.of(2020, 5, 21), Corseplongee);
        licenceJules = new Licence(jules, "456", LocalDate.of(2018, 8, 27), Corseplongee);
    }
    
   
    @Test
    public void testOrganisePlongee(){
        Corseplongee.organisePlongee(Aja);
        assertEquals(Aja, Corseplongee.lesPlongees.get(0), "La plongée n'a pas bien été ajoutée");
    }
    
    @Test
    public void testAjouteParticipant(){
        Aja.ajouteParticipant(licenceBaptiste);
        assertEquals(licenceBaptiste, Aja.lesParticipants.get(0),"Le plongeur n'a pas été ajouté à la plongée");    
    }
    
    @Test
    public void testEstValide(){
        assertTrue(licenceBaptiste.estValide(LocalDate.now()), "La licence n'est pas valide");
    }
    
    @Test
    public void testEmployeurActuel(){
        moniteurMichel.nouvelleEmbauche(Corseplongee, LocalDate.of(2020, 1, 1));
        assertEquals(moniteurMichel.employeurActuel().get() , Corseplongee, "L'employeur actuel est le club Corseplongee");
    }
    @Test 
    public void testEstConforme(){
        Aja.ajouteParticipant(licenceBaptiste);
        assertTrue(Aja.estConforme(),  "La plongée n'est pas conforme car une des licences ou les deux ne sont pas conformes");
    }
    @Test 
    public void testEstConformeV2(){
        Bas.ajouteParticipant(licenceJules);
        assertFalse(Bas.estConforme(),  "La plongée n'est pas conforme car une des licences ou les deux ne sont pas conformes");
    }
    @Test
    public void testgetNom(){
            assertEquals(baptiste.getNom(),"Villedieu","Le nom n'est pas correctement récupéré");
    }
}



