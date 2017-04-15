package com.htisolutions.poolref.models.JSON;

import java.util.List;

public class GameState {

    private Integer stateId;

    private List<Ball> ballLocations;

    public Integer getStateId() {
        return this.stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public List<Ball> getBallLocations() {
        return this.ballLocations;
    }

    public void setBallLocations(List<Ball> ballLocations) {
        this.ballLocations = ballLocations;
    }
}
