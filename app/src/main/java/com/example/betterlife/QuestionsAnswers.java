package com.example.betterlife;

public class QuestionsAnswers {
    public static String questions[] = {
            "What is your goal?",
            "What's your sex?",
            "Are you at risk of any of the following?",
            "How much do you sleep?",
            "Do you smoke?",
            "Are you currently on a specific diet?",
            "How many times a day do you prefer to eat?",

    };
    public static  String choices[][] = {
            {"Lose weight","Gain Muscle","Get fit","Eat healthier"},
            {"Male", "Female"},
            {"Heart disease","diabetes", "I don't have any contraindications"},
            {"Sleep < 5 hours", "Sleep 5-7 hours","Sleep 7-9 hours","Sleep > 9 hours"},
            {"smoke few times","smoke Everyday","Never smoke"},
            {"High Protein","low carbs","not on a specific diet"},
            {"Eat 2 times a day", "Eat 3 times a day","Eats >3 times a day"}
    };

    private String[] dataArrayAnswers;
    public QuestionsAnswers(int size) {
        dataArrayAnswers = new String[size];
    }

    public void setData(int index, String value) {
        if (index >= 0 && index < dataArrayAnswers.length) {
            dataArrayAnswers[index] = value;
        } else {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }
    public int getSize(){
        int size = dataArrayAnswers.length;
        return size;
    }

    public String[] getData() {
        return dataArrayAnswers;
    }
}


