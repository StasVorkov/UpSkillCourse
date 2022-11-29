package com.epam.rd.autotasks.chesspuzzles;

import java.util.Collection;

public interface ChessBoard {
    static ChessBoard of(Collection<ChessPiece> pieces){
        return new Board(pieces);
    }
    
    String state();
}
