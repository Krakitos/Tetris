package com.funtoginot.tetris.view.layout;

/**
 * Created by cdric on 15/05/2014.
 */

import com.funtoginot.tetris.view.cell.CellGrid;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cdric on 15/05/2014.
 */

public class TetrisLayout implements LayoutManager2 {

    private Map<Point, Component> mapComps;

    public TetrisLayout(int rows, int columns) {

        mapComps = new HashMap<>(rows*columns);
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        if (constraints instanceof Point) {

            mapComps.put((Point) constraints, comp);

        } else {

            throw new IllegalArgumentException("Tetris constraints must be a Point");

        }
    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return preferredLayoutSize(target);
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0.5f;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0.5f;
    }

    @Override
    public void invalidateLayout(Container target) {
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        Point[] keys = mapComps.keySet().toArray(new Point[mapComps.size()]);
        for (Point p : keys) {
            if (mapComps.get(p).equals(comp)) {
                mapComps.remove(p);
                break;
            }
        }
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return new CellGrid(mapComps).getPreferredSize();
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return preferredLayoutSize(parent);
    }

    @Override
    public void layoutContainer(Container parent) {
        int width = parent.getWidth();
        int height = parent.getHeight();

        int gridSize = Math.max(width, height);

        CellGrid grid = new CellGrid(mapComps);
        int rowCount = grid.getRowCount();
        int columnCount = grid.getColumnCount();

        int cellSize = gridSize / Math.max(rowCount, columnCount);

        int xOffset = 0;
        int yOffset = 0;

        Map<Integer, List<CellGrid.Cell>> cellRows = grid.getCellRows();
        for (List<CellGrid.Cell> rows : cellRows.values()) {
            for (CellGrid.Cell cell : rows) {
                Point p = cell.getPoint();
                Component comp = cell.getComponent();

                int x = xOffset + (p.x * cellSize);
                int y = yOffset + (p.y * cellSize);

                comp.setLocation(x, y);
                comp.setSize(cellSize, cellSize);

            }
        }

    }


}
