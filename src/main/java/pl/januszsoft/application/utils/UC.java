package pl.januszsoft.application.utils;

public abstract class UC {

    protected void checkThat(ActionPossible actionPossible){
        if (actionPossible.isPossible()){
            return;
        }
        throw new IllegalStateException(actionPossible.explainImpossible());
    }
    protected void checkArguments(ActionPossible actionPossible){
        if (actionPossible.isPossible()){
            return;
        }
        throw new IllegalArgumentException(actionPossible.explainImpossible());
    }

}