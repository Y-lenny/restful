package com.rest.restapi.util.query;

/**
 * Created by lennylv on 2017-1-5.
 */
public class Search {

    private String left;

    private SearchMiddle middle;

    private String right;

    public Search() {
    }

    public Search(String right, SearchMiddle middle, String left) {
        this.right = right;
        this.middle = middle;
        this.left = left;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public SearchMiddle getMiddle() {
        return middle;
    }

    public void setMiddle(SearchMiddle middle) {
        this.middle = middle;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }
}
