package mai_ocean.robot_data_transfer.model.enums;

import lombok.Getter;

public enum RobotStatus {
    OK("ok"),
    DAMAGED("damaged"),
    CONNECTION_LOST("connectionLost");

    @Getter
    public final String status;

    RobotStatus(String status){
        this.status = status;
    }

}
