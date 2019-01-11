package fr.unicornteam.uniflix.model.Suggestion;

import fr.unicornteam.uniflix.model.User;

public class UserSuggest implements Comparable<UserSuggest>{
    private User user;
    private int score = 0;

    public UserSuggest(User user, int score){
        this.user = user;
        this.score = score;
    }

    public User getMedia() {
        return user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return user.getUsername() + " ["+ score +"]";
    }

    @Override
    public int compareTo(UserSuggest ms) {
        return ms.getScore()-this.score;
    }
}
