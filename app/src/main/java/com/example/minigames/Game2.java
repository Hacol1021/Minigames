package com.example.minigames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minigames.databinding.ActivityGame2Binding;

import java.util.ArrayList;
import java.util.Random;

public class Game2 extends AppCompatActivity implements View.OnClickListener, NumberPicker.OnScrollListener {
    ActivityGame2Binding binding;

    ArrayList<Integer> myNumberList = new ArrayList<>();
    ArrayList<Integer> randomNumberList = new ArrayList<>();

    ArrayList<TextView> myNumberTextView = new ArrayList<>();
    ArrayList<TextView> randomNumberTextview = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGame2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //스크롤에 1~ 45의 숫자가 나오도록하기
        binding.numberPicker.setMinValue(1);
        binding.numberPicker.setMaxValue(45);
        Random r = new Random();
        binding.numberPicker.setValue(r.nextInt(45) + 1);

        //공을 그리는 텍스트뷰를 리스트에 관리하기
        myNumberTextView.add(binding.myBall1);
        myNumberTextView.add(binding.myBall2);
        myNumberTextView.add(binding.myBall3);
        myNumberTextView.add(binding.myBall4);
        myNumberTextView.add(binding.myBall5);
        myNumberTextView.add(binding.myBall6);

        randomNumberTextview.add(binding.robotBall1);
        randomNumberTextview.add(binding.robotBall2);
        randomNumberTextview.add(binding.robotBall3);
        randomNumberTextview.add(binding.robotBall4);
        randomNumberTextview.add(binding.robotBall5);
        randomNumberTextview.add(binding.robotBall6);


        binding.numberPicker.setOnScrollListener(this);
        binding.btnAdd.setOnClickListener(this);
        binding.btnClear.setOnClickListener(this);
        binding.btnRun.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                addOneBall();
                for(int i = 0; i < myNumberList.size(); i++){
                    myNumberTextView.get(i).setText(Integer.toString(myNumberList.get(i)));
                    myNumberTextView.get(i).setVisibility(view.VISIBLE);

                }

                break;
            case R.id.btn_clear:
                for(int i = 0; i < myNumberList.size(); i++){
                    myNumberTextView.get(i).setVisibility(view.INVISIBLE);
                    myNumberTextView.get(i).setText("0");
                }
                myNumberList.clear();
                binding.numberPicker.setEnabled(true);
                break;
            case R.id.btn_run:
                //랜덤 숫자 뽑기
                getRandomNumber();
                int win = 0;

                for (Integer a: myNumberList){
                    if(randomNumberList.contains((a))){
                        win++;
                    }
                }
                binding.txtMessage.setText("당첨수:" + win);

                break;

        }
    }

    private void getRandomNumber() {
        if(randomNumberList.size() == 6){
            randomNumberList.clear();
        }
        Random r = new Random();
        Integer number;
        while(randomNumberList.size() < 6){
            number = r.nextInt(45) + 1;
            if(randomNumberList.contains(number))
                continue;
            randomNumberTextview.get(randomNumberList.size()).setText(number.toString());
            randomNumberList.add(number);
        }
    }

    private void addOneBall() {
        //숫자를 리스트에 저장
        if(myNumberList.size() == 6){
            Toast.makeText(this,"6개 모두 선택했습니다.", Toast.LENGTH_SHORT).show();
            binding.numberPicker.setEnabled(false);
            return;
        }
        int number = binding.numberPicker.getValue();
        if(myNumberList.contains(number)){
            Toast.makeText(this, "이미 선택한 숫자입니다.", Toast.LENGTH_SHORT).show();
        }else{
            myNumberList.add(number);

        }

    }


    @Override
    public void onScrollStateChange(NumberPicker numberPicker, int i) {
        if(binding.switch1.isChecked()){
            if(i == SCROLL_STATE_IDLE)
                binding.btnAdd.performClick();
        }

    }
}