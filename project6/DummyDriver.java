public class DummyDriver {
    // tests some interfaces and functionality for DogTeam class of Project #6
    // D.A.M. Barrington, 6 November 2011
    public static void main (String [ ] args) {
        DogTeam [ ] entries = new DogTeam[6];
        for (int i = 0; i < 6; i++)
            entries[i] = new DogTeam( );
        entries[0].addToLead (new SledDog ("Youk", 3, "Husky", 0));
        entries[0].addToLead (new SledDog ("Big Papi", 4, "Husky", 0));
        entries[0].addToLead (new SledDog ("Gonzo", 5, "Husky", 0));
        entries[0].addToLead (new SledDog ("Petey", 2, "Husky", 0));
        entries[0].addToLead (new SledDog ("Ells", 7, "Husky", 0));
        entries[1].addToLead (new SledDog ("Cano", 3, "Husky", 1));
        entries[1].addToLead (new SledDog ("Granderson", 4, "Husky", 1));
        entries[1].addToLead (new SledDog ("Jeter", 6, "Husky", 1));
        entries[1].addToLead (new SledDog ("Swisher", 2, "Husky", 1));
        entries[1].addToLead (new SledDog ("A-Rod", 8, "Husky", 1));
        entries[2].addToLead (new SledDog ("Blitzen", 3, "Husky", 2));
        entries[2].addToLead (new SledDog ("Donner", 3, "Husky", 2));
        entries[2].addToLead (new SledDog ("Cupid", 5, "Husky", 2));
        entries[2].addToLead (new SledDog ("Comet", 3, "Husky", 2));
        entries[2].addToLead (new SledDog ("Vixen", 4, "Husky", 2));
        entries[2].addToLead (new SledDog ("Prancer", 6, "Husky", 2));
        entries[2].addToLead (new SledDog ("Dancer", 3, "Husky", 2));
        entries[2].addToLead (new SledDog ("Dasher", 2, "Husky", 2));
        entries[3].addToLead (new SledDog ("Homer", 3, "Husky", 3));
        entries[3].addToLead (new SledDog ("Lisa", 3, "Husky", 3));
        entries[3].addToLead (new SledDog ("Bart", 3, "Husky", 3));
        entries[3].addToLead (new SledDog ("Marge", 3, "Husky", 3));
        entries[3].addToLead (new SledDog ("Mr. Burns", 3, "Husky", 3));
        entries[3].addToLead (new SledDog ("Smithers", 3, "Husky", 3));
        entries[4].addToLead (new SledDog ("Swinburne", 3, "Husky", 4));
        entries[4].addToLead (new SledDog ("Rosetti", 5, "Husky", 4));
        entries[4].addToLead (new SledDog ("Tennyson", 7, "Husky", 4));
        entries[4].addToLead (new SledDog ("Bronte", 3, "Husky", 4));
        entries[4].addToLead (new SledDog ("Barrett Browning", 6, "Husky", 4));
        entries[4].addToLead (new SledDog ("Fitzgerald", 3, "Husky", 4));
        entries[4].addToLead (new SledDog ("Yeats", 2, "Husky", 4));
        entries[5].addToLead (new SledDog ("Swedish Chef", 3, "Husky", 5));
        entries[5].addToLead (new SledDog ("Gonzo", 3, "Husky", 5));
        entries[5].addToLead (new SledDog ("Animal", 3, "Husky", 5));
        entries[5].addToLead (new SledDog ("Kermit", 3, "Husky", 5));
        entries[5].addToLead (new SledDog ("Miss Piggy", 3, "Husky", 5));
        entries[5].addToLead (new SledDog ("Statler", 11, "Husky", 5));
        entries[5].addToLead (new SledDog ("Beaker", 12, "Husky", 5));
        entries[5].addToLead (new SledDog ("Fozzie", 12, "Husky", 5));
        entries[5].addToLead (new SledDog ("Janice", 12, "Husky", 5));
        entries[5].addToLead (new SledDog ("Sam the Eagle", 12, "Husky", 5));
        entries[5].addToLead (new SledDog ("Waldorf", 12, "Husky", 5));
        
	DogTeam sortedEntries = DogTeam.mergeSortTeams (entries);

        // used enhanced for (creating an iterator) to list team members
	for (SledDog sd : sortedEntries)
	    System.out.println (sd.getName( ));
    }


    

}