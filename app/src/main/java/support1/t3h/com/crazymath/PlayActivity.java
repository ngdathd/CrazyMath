package support1.t3h.com.crazymath;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {
    private int number1;
    private int number2;
    private int answer;
    private int result;
    private int point;
    private Random rd;
    private ImageView ivTrue;
    private ImageView ivFalse;
    private TextView tvScore;
    private TextView tvTime;
    private TextView tvNumber1;
    private TextView tvNumber2;
    private TextView tvAnswer;
    private Thread thread;
    private Handler mHander;
    private CountDownTimer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        findViewByIds();
        initComponents();
        createQuestion();
        setEvents();

    }

    private void findViewByIds() {
        ivTrue = (ImageView) findViewById(R.id.iv_true);
        ivFalse = (ImageView) findViewById(R.id.iv_false);
        tvNumber1 = (TextView) findViewById(R.id.tv_number1);
        tvNumber2 = (TextView) findViewById(R.id.tv_number2);
        tvAnswer = (TextView) findViewById(R.id.tv_answer);
        tvScore = (TextView) findViewById(R.id.tv_score);
        tvTime = (TextView) findViewById(R.id.tv_time);

        tvScore.setText("0");
    }

    private void initComponents() {
        point = 0;
        rd = new Random();
//        thread = new Thread(this);
//        thread.start();

        mHander = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
                    tvScore.setText((String) msg.obj);
                } else {
                    tvTime.setText((String) msg.obj);
                }
            }
        };
    }

    private void setEvents() {
        ivTrue.setOnClickListener(this);
        ivFalse.setOnClickListener(this);
    }

    private void createQuestion() {
        number1 = rd.nextInt(50);
        number2 = rd.nextInt(50);
        result = number1 + number2;

        if (number1 % 2 == 0) {
            answer = result;
        } else {
            answer = result + rd.nextInt(5);
        }

        tvNumber1.setText(number1 + "");
        tvNumber2.setText(number2 + "");
        tvAnswer.setText(answer + "");

//        countDownTime();
    }

//    private void countDownTime() {
//         mTimer = new CountDownTimer(3000, 0) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                int time = (int) millisUntilFinished / 1000;
//                tvTime.setText(String.valueOf(time));
//                if (time == 0) {
//                    Toast.makeText(PlayActivity.this, "Hết thời gian", Toast.LENGTH_SHORT).show();
////                    Thread thread1 = new Thread(new Runnable() {
////                        @Override
////                        public void run() {
////                            SystemClock.sleep(1000);
////                            Intent intent = new Intent(PlayActivity.this, MainActivity.class);
////                            startActivity(intent);
////                        }
////                    });
////                    thread1.start();
//                    finish();
//                }
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        }.start();
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_true:
                checkTrueClicked();
                break;
            case R.id.iv_false:
                checkFalseClicked();
                break;
            default:
                break;
        }
    }

    private void checkFalseClicked() {
        Message message = new Message();
        if (result != answer) {
            //tao cau hoi moi
            createQuestion();

            //cong diem
            point++;
            message.what = 0;
            message.obj = point + "";
            message.setTarget(mHander);
            message.sendToTarget();

            //thong bao tra loi dung
            Toast.makeText(this, "Đúng", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Sai", Toast.LENGTH_SHORT).show();

            //tro ve man hinh chinh
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(1000);
                    Intent intent = new Intent(PlayActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            thread1.start();
        }
    }

    private void checkTrueClicked() {
        Message message = new Message();
        if (result == answer) {
            //tao cau hoi moi
            createQuestion();

            //cong diem
            point++;
            message.what = 0;
            message.obj = point + "";
            message.setTarget(mHander);
            message.sendToTarget();

            //thong bao tra loi dung
            Toast.makeText(this, "Đúng", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Sai", Toast.LENGTH_SHORT).show();

            //tro ve man hinh chinh
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(1000);
                    Intent intent = new Intent(PlayActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            thread1.start();
        }
    }

}
