package fr.unicornteam.uniflix.model;

public class Search {
    public String searchValue;

    public Search () {
        searchValue = new String();
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
}
