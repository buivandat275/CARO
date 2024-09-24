package com.example.democaro2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.gridlayout.widget.GridLayout;

public class caro33 extends AppCompatActivity {

    private int size = 3;
    private Button[][] buttons = new Button[size][size];
    private boolean playerXTurn = true;
    private int[][] board = new int[size][size]; // 0: empty, 1: X, 2: O
    private TextView playerTurnTextView;
    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_caro33);
        overridePendingTransition(R.anim.slide1, R.anim.slide2);
        playerTurnTextView = findViewById(R.id.textView);
        gridLayout = findViewById(R.id.GridLayout);


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final int x = i;
                final int y = j;
                buttons[i][j] = new Button(this);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 0;
                params.height = 0;
                params.columnSpec = GridLayout.spec(j, 1, 1f);
                params.rowSpec = GridLayout.spec(i, 1, 1f);
                buttons[i][j].setLayoutParams(params);
                buttons[i][j].setTextSize(80);
                buttons[i][j].setPadding(0, 0, 0, 0);
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (board[x][y] == 0) {
                            if (playerXTurn) {
                                ((Button) view).setText("X");
                                ((Button) view).setTextColor(Color.parseColor("#FF0000"));
                                board[x][y] = 1;
                                playerTurnTextView.setText("Lượt chơi của O");
                            } else {
                                ((Button) view).setText("O");
                                ((Button) view).setTextColor(Color.parseColor("#0000FF"));
                                board[x][y] = 2;
                                playerTurnTextView.setText("Lượt chơi của X");
                            }
                            playerXTurn = !playerXTurn;
                            checkWin();
                        }
                    }
                });
                gridLayout.addView(buttons[i][j]);
            }
        }
    }


    private void checkWin() {
        if (checkWinner(1)) {
            showWinDialog("Người chơi X");
        } else if (checkWinner(2)) {
            showWinDialog("Người chơi O");
        }else if (hetO()) {
            showWinDialog("Hòa");
        }
    }
    private boolean hetO() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    private void showWinDialog(String winner) {
        int xCount = 0;
        int oCount = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 1) {
                    xCount++;
                } else if (board[i][j] == 2) {
                    oCount++;
                }
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Kết quả");
        if(winner.equals("Hòa")){
            builder.setMessage("Hòa"+ "\nX:" + xCount +";"+ "\nO: "+oCount  );
        }else{
            builder.setMessage(winner + " đã thắng!"+ "\nX:" + xCount +";"+ "\nO: "+oCount  );
        }

        builder.setPositiveButton("Chơi lại", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetBoard();
            }
        });

        builder.setNegativeButton("Về menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(caro33.this, CheDo.class);
                startActivity(intent);
                finish(); // Kết thúc MainActivity
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean checkWinner(int player) {
        // Kiểm tra hàng ngang và hàng dọc
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size-2 ; j++) {
                if (board[i][j] == player && board[i][j + 1] == player && board[i][j + 2] == player ) {
                    return true;
                }
                if (board[j][i] == player && board[j + 1][i] == player && board[j + 2][i] == player ) {
                    return true;
                }
            }
        }

        // Kiểm tra đường chéo
        for (int i = 0; i < size - 2; i++) {
            for (int j = 0; j < size - 2; j++) {
                if (board[i][j] == player && board[i + 1][j + 1] == player && board[i + 2][j + 2] == player ) {
                    return true;
                }
                if (board[i + 2][j] == player && board[i + 1][j + 1] == player && board[i ][j + 2] == player ) {
                    return true;
                }
            }
        }

        return false;
    }

    private void resetBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = 0;
                buttons[i][j].setText("");
            }
        }
        playerXTurn = true;
        playerTurnTextView.setText("Lượt của người chơi X");
    }
}