package com.example.betterlife;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Survey extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
    TextView questionTextView;
    TextView questionNum;
    Button submitBtn;
    LinearLayout choicesL;
    int totalQuestion = QuestionsAnswers.questions.length;
    String selectedBtn = "";
    int currentIn = 0; // keeps track of the index of the current question being displayed
    int questionNumber = 1;
    int sizeQ = 7 ; // total number of questions
    QuestionsAnswers QuestionsAnswersObject = new QuestionsAnswers(sizeQ);
    boolean imageDisplaying = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        totalQuestionsTextView = findViewById(R.id.total_question);
        questionNum = findViewById(R.id.num_question);
        questionTextView = findViewById(R.id.question);

        choicesL = findViewById(R.id.choices_layout);
        submitBtn = findViewById(R.id.submit_btn);

        questionTextView.setVisibility(View.GONE);// Hide the question
        choicesL.setVisibility(View.GONE); // Hide Layout of choices
        submitBtn.setVisibility(View.GONE); // Hide the submit Button

        submitBtn.setOnClickListener(this);
        totalQuestionsTextView.setText("Total questions : " + totalQuestion);
        questionNum.setText("Question Number : " + questionNumber);

          // Display first question
           String question = QuestionsAnswers.questions[currentIn];
            choicesL.setVisibility(View.VISIBLE);
            if (question != null ) {
                // show the question
                questionTextView.setVisibility(View.VISIBLE);
                submitBtn.setVisibility(View.VISIBLE);

            } else {

            }
        // Display the options of the first question
        for (int ansIndex = 0 ; ansIndex < QuestionsAnswers.choices[currentIn].length; ansIndex++){
            String answer = QuestionsAnswers.choices[currentIn][ansIndex];
            Button button = new Button(this);
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setText(answer);
            button.setBackgroundColor(Color.WHITE);
            button.setId(currentIn);

            choicesL.addView(button);
             // Get the existing layout parameters of the button
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
            // Set the desired margin, padding value
            int marginEnd = 16; // in pixels
            int padding = 16;
            int margin = 5;
            // Convert the margin value from pixels to dp
            float marginDp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, margin, getResources().getDisplayMetrics());
            // Update the marginEnd property
            layoutParams.setMarginEnd(marginEnd);
            button.setPadding(padding, padding, padding, padding);  // Apply the padding to all sides of the button
            layoutParams.setMargins((int) marginDp, (int) marginDp, (int) marginDp, (int) marginDp); //Set the margin on all sides of the button
            // Apply the updated layout parameters to the button
            button.setLayoutParams(layoutParams);
            // the first time the user click the answers of the first question
            button.setOnTouchListener(new View.OnTouchListener(){
                @Override
                public boolean onTouch (View v , MotionEvent event){
                    if(imageDisplaying){
                        button.setBackgroundColor(Color.LTGRAY); // change color of the option chosen by user
                       // Toast.makeText(getApplicationContext(), "Button Clicked", Toast.LENGTH_SHORT).show();
                        selectedBtn = button.getText().toString();
                        Toast.makeText(getApplicationContext(), "Value: " + selectedBtn, Toast.LENGTH_SHORT).show();
                        QuestionsAnswersObject.setData(currentIn, selectedBtn); // saved user's answer

                        imageDisplaying = false;
                    }else{
                        button.setBackgroundColor(Color.WHITE);
                        imageDisplaying = true;
                    }
                    return false;
                }
            });



        }




    }
    Boolean isFinishQuizz = false;
    String recommendation= "";
    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;

        if (clickedButton.getId()==R.id.submit_btn && !isFinishQuizz ){ // if the user clicks submit  && the quizzes has not finished
            currentIn++;
            questionNumber++;
            loadNewQuestion();
        }else if (clickedButton.getId()==R.id.submit_btn && isFinishQuizz){ // if the user click submit
              // when quizzes has finished  and results already showed
              // showed recommendations
              getRecommendations();
        }else{ //  if the user clicks submit and  the Quizzes has finished  && recommendations already showed


            // After showing the users results , redirect the user to gym/ restaurant
            // Intent intent = new Intent(getApplicationContext(), Homepage.class);
            // startActivity(intent);
        }

    }

    void loadNewQuestion(){
        imageDisplaying = true;
        if(currentIn == totalQuestion){
            isFinishQuizz = true;
            finishQuiz();
            return;
        }
        Toast.makeText(getApplicationContext(), "INDEX: " + currentIn, Toast.LENGTH_SHORT).show();
        // Removing all options_buttons in the layout choices
        LinearLayout layout = findViewById(R.id.choices_layout);
        layout.removeAllViews();
        // Display new question
        questionNum.setText("Question Number : " + questionNumber );
        questionTextView.setText(QuestionsAnswers.questions[currentIn]);
        // Displaying the options of each new question
        for (int ansIndex = 0 ; ansIndex < QuestionsAnswers.choices[currentIn].length; ansIndex++){
            String answer = QuestionsAnswers.choices[currentIn][ansIndex];
            Button button = new Button(this);
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setText(answer);
            button.setBackgroundColor(Color.WHITE);
            choicesL.addView(button);
            // Get the existing layout parameters of the button
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
            // Set the desired margin, padding value
            int marginEnd = 16; // in pixels
            int padding = 16;
            int margin = 5;
            // Convert the margin value from pixels to dp
            float marginDp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, margin, getResources().getDisplayMetrics());
            // Update the marginEnd property
            layoutParams.setMarginEnd(marginEnd);
            button.setPadding(padding, padding, padding, padding);  // Apply the padding to all sides of the button
            layoutParams.setMargins((int) marginDp, (int) marginDp, (int) marginDp, (int) marginDp); //Set the margin on all sides of the button
            // Apply the updated layout parameters to the button
            button.setLayoutParams(layoutParams);
            // When user chooses an option:
            button.setOnTouchListener(new View.OnTouchListener(){
                @Override
                public boolean onTouch (View v , MotionEvent event){
                    if(imageDisplaying){
                        button.setBackgroundColor(Color.LTGRAY); // change color of the option chosen by user
                        Toast.makeText(getApplicationContext(), "Button Clicked", Toast.LENGTH_SHORT).show();
                        selectedBtn = button.getText().toString();
                        Log.d("TAG", "Value: " + selectedBtn);
                        Toast.makeText(getApplicationContext(), "Value: " + selectedBtn, Toast.LENGTH_SHORT).show();
                        QuestionsAnswersObject.setData(currentIn, selectedBtn); // saved user's answer
                        imageDisplaying = false;
                    }else{
                        button.setBackgroundColor(Color.WHITE);
                        imageDisplaying = true;
                    }
                    return false;
                }
            });


        }
    }

    void finishQuiz(){
        totalQuestionsTextView.setVisibility(View.GONE);
        questionNum.setVisibility(View.GONE);
        // Removing all options buttons in the layout choices
        choicesL.removeAllViews();
        // Setting the title of the new view
        questionTextView.setText("These are your answers:");
        // Updating the text of button submit by "NEXT"
        submitBtn.setText("NEXT");
        // Getting an array with the user's answers
        String arrAns[] = QuestionsAnswersObject.getData();
        // Displaying information selected by the user
        for (int ansIn = 0; ansIn < QuestionsAnswersObject.getSize(); ansIn++){
            Button button = new Button(this);
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setText(arrAns[ansIn]);
            button.setBackgroundColor(Color.WHITE);
            choicesL.addView(button);
            // Get the existing layout parameters of the button
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
            // Set the desired margin, padding value
            int marginEnd = 16; // in pixels
            int padding = 16;
            int margin = 5;
            // Convert the margin value from pixels to dp
            float marginDp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, margin, getResources().getDisplayMetrics());
            // Update the marginEnd property
            layoutParams.setMarginEnd(marginEnd);
            button.setPadding(padding, padding, padding, padding);  // Apply the padding to all sides of the button
            layoutParams.setMargins((int) marginDp, (int) marginDp, (int) marginDp, (int) marginDp); //Set the margin on all sides of the button
            // Apply the updated layout parameters to the button
            button.setLayoutParams(layoutParams);


        }


    }
    void getRecommendations(){
        // Removing all option buttons in the layout choices
        choicesL.removeAllViews();
        // Removing the submit button
        submitBtn.setVisibility(View.GONE);

        // Set the desired margin, padding value for the buttons that we will create later
        int marginEnd = 16; // in pixels
        int padding = 16;
        int margin = 5;
        // Convert the margin value from pixels to dp
        float marginDp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, margin, getResources().getDisplayMetrics());

        // Creating a button Gym which will redirect the user to the nearest gyms
        Button btn_Gym = new Button(this);
        btn_Gym.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        btn_Gym.setBackgroundColor(Color.GREEN);
        LinearLayout.LayoutParams layoutParamsGym = (LinearLayout.LayoutParams) btn_Gym.getLayoutParams();
       // Update the marginEnd property
        layoutParamsGym.setMarginEnd(marginEnd);
        btn_Gym.setPadding(padding, padding, padding, padding);  // Apply the padding to all sides of the button
        layoutParamsGym.setMargins((int) marginDp, (int) marginDp, (int) marginDp, (int) marginDp); //Set the margin on all sides of the button
        // Apply the updated layout parameters to the button
        btn_Gym.setLayoutParams(layoutParamsGym);
        btn_Gym.setText("Show nearest gyms");
        btn_Gym.setTextColor(Color.WHITE);

        // Creating a button Restaurants which will redirect the user to the restaurants
        Button btn_Restaurants = new Button(this);
        btn_Restaurants.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        btn_Restaurants.setBackgroundColor(Color.GREEN);
        LinearLayout.LayoutParams layoutParamsRestaurant = (LinearLayout.LayoutParams) btn_Restaurants.getLayoutParams();
        // Update the marginEnd property
        layoutParamsRestaurant.setMarginEnd(marginEnd);
        btn_Restaurants.setPadding(padding, padding, padding, padding);  // Apply the padding to all sides of the button
        layoutParamsRestaurant.setMargins((int) marginDp, (int) marginDp, (int) marginDp, (int) marginDp); //Set the margin on all sides of the button
        // Apply the updated layout parameters to the button
        btn_Restaurants.setLayoutParams(layoutParamsRestaurant);
        btn_Restaurants.setText("Show nearest Restaurants");
        btn_Restaurants.setTextColor(Color.WHITE);



        // Displaying recommendations
        String arrAns[] = QuestionsAnswersObject.getData();
        totalQuestionsTextView.setVisibility(View.GONE);
        questionNum.setVisibility(View.GONE);
        questionTextView.setText("RECOMMENDATIONS:");
        recommendation = "We recommend you the following: ";
        Button btn_recommendations = new Button(this);
        btn_recommendations.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        btn_recommendations.setBackgroundColor(Color.WHITE);
        btn_recommendations.setTextColor(Color.BLACK);
        for (int ansIn = 0; ansIn < QuestionsAnswersObject.getSize(); ansIn++){

            if (  (arrAns[ansIn] == "Lose weight" || arrAns[ansIn] == "Gain Muscle"  ||  arrAns[ansIn] == "Get fit") && ansIn == 0){

                if(arrAns[ansIn] == "Lose weight"){
                    recommendation+= "If you wanna lose weight go to the GYM 3-5 times per week and eat low carbs. ";
                }else if (arrAns[ansIn] == "Gain Muscle"){
                    recommendation+= "If you wanna gain muscle go to the GYM 3-5 times per week and eat high protein. ";
                }
                else if (arrAns[ansIn] == "Get fit"){
                    recommendation+= "If you wanna get fit go to the GYM 3-5 times per week and eat a balanced and nutritious meal. ";
                }
                recommendation+= "We will show you the nearest gyms. " ;
                //button.setText("We recommend you to go to the GYM 3-5 times per week. We will show you the nearest gyms available. ");
            }
            if ( (arrAns[ansIn] == "Eat healthier") && ansIn == 0){
                recommendation+="Eating healthier is always a good decision. We will show you the healthiest restaurants nearby.";
                // button.setText("We recommend you the healthiest restaurants nearby");
            }
            if ( (arrAns[ansIn] == "diabetes" ) && ansIn == 2){
                recommendation+="Remember to take your medication and consult your doctor what kind of exercises better suit your condition.";
                //button.setText("We recommend you the healthiest restaurants nearby");
            }
            if ( (arrAns[ansIn] == "Heart disease" ) && ansIn == 2){
                recommendation+="Do moderate exercise and always consult your doctor what kind of exercises better suit your condition.";
                //button.setText("We recommend you the healthiest restaurants nearby");
            }
            if (  (arrAns[ansIn] == "smoke Everyday" ) && ansIn == 4){
                recommendation+= "Decrease smoking. ";
                //button.setText("We recommend you to go to the GYM 3-5 times per week. We will show you the nearest gyms available. ");
            }
            if (  (arrAns[ansIn] == "smoke few times" ) && ansIn == 4){
                recommendation+= "Quit Smoking. ";
                //button.setText("We recommend you to go to the GYM 3-5 times per week. We will show you the nearest gyms available. ");
            }
            if (  (arrAns[ansIn] == "Eat 2 times a day" ) && ansIn == 6){
                recommendation+= "Eat 3 proper nutritious meals a day";
                //button.setText("We recommend you to go to the GYM 3-5 times per week. We will show you the nearest gyms available. ");
            }
            if (  (arrAns[ansIn] == "Eats >3 times a day" ) && ansIn == 6){
                recommendation+= "Eat 3 proper meals a day and have some healthy snacks such as nuts, or fruits in between ";
                //button.setText("We recommend you to go to the GYM 3-5 times per week. We will show you the nearest gyms available. ");
            }
        }
        btn_recommendations.setText(recommendation);
        // Get the existing layout parameters of the button
        LinearLayout.LayoutParams layoutParamsRecommendations = (LinearLayout.LayoutParams) btn_recommendations.getLayoutParams();
        // Update the marginEnd property
        layoutParamsRecommendations.setMarginEnd(marginEnd);
        btn_recommendations.setPadding(padding, padding, padding, padding);  // Apply the padding to all sides of the button
        layoutParamsRecommendations.setMargins((int) marginDp, (int) marginDp, (int) marginDp, (int) marginDp); //Set the margin on all sides of the button
        // Apply the updated layout parameters to the button
        btn_recommendations.setLayoutParams(layoutParamsRecommendations);
        // Adding the 3 buttons to Choices Layout
        choicesL.addView(btn_recommendations);
        choicesL.addView(btn_Gym);
        choicesL.addView(btn_Restaurants);

    }
}
