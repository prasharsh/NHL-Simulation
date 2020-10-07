package g4dhl;

public class GeneralManager implements IGeneralManager {
    private String generalManagerName;

    public GeneralManager(){
        generalManagerName = null;
    }

    @Override
    public int getGeneralManagerId() {
        return 0;
    }

    @Override
    public String getGeneralManagerName(){
        return generalManagerName;
    }

    @Override
    public void setGeneralManagerId(int id) {

    }

    @Override
    public void setGeneralManagerName(String generalManagerName) {
        this.generalManagerName = generalManagerName;
    }
}
