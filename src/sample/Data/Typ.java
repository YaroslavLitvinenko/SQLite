package sample.Data;

import java.text.NumberFormat;

public enum Typ {
    Magazine ("Журнал"),
    Book ("Книга"),
    Newspaper("Газета"),
    Bulletin("Бюллетень"),
    Brochure("Брошюра"),
    Directory("Каталог");

    private String name;

    Typ(String name) {
        this.name=name;
    }

    public boolean Equals (String name){
        if (this.name == name)
            return true;
        else return false;
    }

    public String getName (){
        return name;
    }

    public static Typ get (String name) throws NumberFormatException{
        switch (name){
            case "Журнал":
                return Magazine;
            case "Книга":
                return Book;
            case "Газета":
                return Newspaper;
            case "Бюллетень":
                return Bulletin;
            case "Брошюра":
                return Brochure;
            case "Каталог":
                return Directory;
        }
        throw new NumberFormatException("Не предусмотренный вариант");
    }
}

