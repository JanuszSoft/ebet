package pl.januszsoft.application.utils;

public class ActionPossible {

    private boolean possible;
    private String  explanation;

    private ActionPossible(boolean possible, String explanation){
        this.possible = possible;
        this.explanation = explanation;
    }

    public static ActionPossible possible(){
        return new ActionPossible(true,"possible");
    }
    public static ActionPossible impossible(String explanation){
        return new ActionPossible(false, explanation);
    }


    public boolean isPossible() {
        return possible;
    }

    public String explainImpossible() {
        return explanation;
    }
}