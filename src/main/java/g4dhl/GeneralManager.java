package g4dhl;

public class GeneralManager implements IGeneralManager {

    int generalManagerId;
    private String generalManagerName;

    public GeneralManager(){
        generalManagerName = null;
    }

    @Override
    public int getGeneralManagerId() {
        return generalManagerId;
    }

    @Override
    public String getGeneralManagerName(){
        return generalManagerName;
    }

    @Override
    public void setGeneralManagerId(int generalManagerId) {
        this.generalManagerId = generalManagerId;
    }

    @Override
    public void setGeneralManagerName(String generalManagerName) {
        this.generalManagerName = generalManagerName;
    }
}
