package uk.ac.ucl.cs.passawis;

public class Tuple {

    private StringArray included;
    private StringArray excluded;

    public Tuple(StringArray included, StringArray excluded){
        this.included = included;
        this.excluded = excluded;
    }

    public StringArray getIncluded(){return this.included;}

    public StringArray getExcluded(){return this.excluded;}

}
