package de.exxellent.challenge.modul;

import javax.persistence.*;

@Entity
public class Football {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer team_id;

    private String team;
    private Integer goals_made;
    private Integer goals_allowed;

    public Football() {
    }

    public Football( String team, Integer goals_made, Integer goals_allowed) {
        this.team = team;
        this.goals_made = goals_made;
        this.goals_allowed = goals_allowed;
    }
    public Football(Integer team_id, String team, Integer goals_made, Integer goals_allowed) {
        this.team_id = team_id;
        this.team = team;
        this.goals_made = goals_made;
        this.goals_allowed = goals_allowed;
    }


    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Integer getGoals_made() {
        return goals_made;
    }

    public void setGoals_made(Integer goals_made) {
        this.goals_made = goals_made;
    }

    public Integer getGoals_allowed() {
        return goals_allowed;
    }

    public void setGoals_allowed(Integer goals_allowed) {
        this.goals_allowed = goals_allowed;
    }

    @Override
    public String toString() {
        return "Football{" +
                "team_id=" + team_id +
                ", team='" + team + '\'' +
                ", goals_made=" + goals_made +
                ", goals_allowed=" + goals_allowed +
                '}';
    }
}
