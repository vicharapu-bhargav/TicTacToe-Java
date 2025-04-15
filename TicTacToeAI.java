public class TicTacToeAI {

  public int findBestMove(String[] board, boolean isX) {
      int bestScore = isX ? Integer.MIN_VALUE : Integer.MAX_VALUE;
      int bestMove = -1;

      for (int i = 0; i < board.length; i++) {
          if (board[i].equals("")) {
              board[i] = isX ? "X" : "O";
              int score = minimax(board, !isX);
              board[i] = "";

              if (isX && score > bestScore || !isX && score < bestScore) {
                  bestScore = score;
                  bestMove = i;
              }
          }
      }

      return bestMove;
  }

  private int minimax(String[] board, boolean isX) {
      String winner = checkWinner(board);
      if (winner != null) {
          if (winner.equals("X")) return 1;
          if (winner.equals("O")) return -1;
          if (winner.equals("Draw")) return 0;
      }

      int bestScore = isX ? Integer.MIN_VALUE : Integer.MAX_VALUE;

      for (int i = 0; i < board.length; i++) {
          if (board[i].equals("")) {
              board[i] = isX ? "X" : "O";
              int score = minimax(board, !isX);
              board[i] = "";

              if (isX) bestScore = Math.max(score, bestScore);
              else bestScore = Math.min(score, bestScore);
          }
      }

      return bestScore;
  }

  private String checkWinner(String[] b) {
      int[][] combos = {
          {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
          {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
          {0, 4, 8}, {2, 4, 6}
      };

      for (int[] combo : combos) {
          String a = b[combo[0]];
          String c = b[combo[1]];
          String d = b[combo[2]];
          if (!a.equals("") && a.equals(c) && c.equals(d)) return a;
      }

      for (String cell : b) {
          if (cell.equals("")) return null;
      }

      return "Draw";
  }
}
