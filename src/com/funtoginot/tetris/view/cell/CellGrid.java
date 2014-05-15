package com.funtoginot.tetris.view.cell;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cdric on 15/05/2014.
 */
public class CellGrid {

    private Dimension prefSize;
    private int cellWidth;
    private int cellHeight;

    private Map<Integer, List<Cell>> mapRows;
    private Map<Integer, List<Cell>> mapCols;

    public CellGrid(Map<Point, Component> mapComps) {
        mapRows = new HashMap<>(25);
        mapCols = new HashMap<>(25);
        for (Point p : mapComps.keySet()) {
            int row = p.y;
            int col = p.x;
            List<Cell> rows = mapRows.get(row);
            List<Cell> cols = mapCols.get(col);
            if (rows == null) {
                rows = new ArrayList<>(25);
                mapRows.put(row, rows);
            }
            if (cols == null) {
                cols = new ArrayList<>(25);
                mapCols.put(col, cols);
            }
            Cell cell = new Cell(p, mapComps.get(p));
            rows.add(cell);
            cols.add(cell);
        }

        int rowCount = mapRows.size();
        int colCount = mapCols.size();

        cellWidth = 0;
        cellHeight = 0;

        for (List<Cell> comps : mapRows.values()) {
            for (Cell cell : comps) {
                Component comp = cell.getComponent();
                cellWidth = Math.max(cellWidth, comp.getPreferredSize().width);
                cellHeight = Math.max(cellHeight, comp.getPreferredSize().height);
            }
        }

        int cellSize = Math.max(cellHeight, cellWidth);

        prefSize = new Dimension(cellSize * colCount, cellSize * rowCount);
    }

    public int getRowCount() {
        return getCellRows().size();
    }

    public int getColumnCount() {
        return getCellColumns().size();
    }

    public Map<Integer, List<Cell>> getCellColumns() {
        return mapCols;
    }

    public Map<Integer, List<Cell>> getCellRows() {
        return mapRows;
    }

    public Dimension getPreferredSize() {
        return prefSize;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public class Cell {

        private Point point;
        private Component component;

        public Cell(Point p, Component comp) {
            this.point = p;
            this.component = comp;
        }

        public Point getPoint() {
            return point;
        }

        public Component getComponent() {
            return component;
        }

    }

}