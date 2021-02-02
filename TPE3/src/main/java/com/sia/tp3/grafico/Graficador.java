package com.sia.tp3.grafico;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Graficador extends JFrame {

    public static final String DESEMPENIO_PROMEDIO_TITULO = "Desempeño Promedio",
            MEJOR_DESEMPENIO_TITULO = "Mejor Desempeño", PEOR_DESEMPENIO_TITULO= "Peor Desempeño",
            GRAFICO_TITULO = "Gráfico de Desempeño", X_TITULO = "Generación",
            Y_TITULO= "Desempeño";

    public void initUI(final List<Punto2D> mejoresDesempenios, final List<Punto2D> peoresDesempenios, final List<Punto2D> desempenioPromedio) {

        XYDataset dataset = createDataset(mejoresDesempenios, peoresDesempenios, desempenioPromedio);
        JFreeChart chart = createChart(dataset, GRAFICO_TITULO, X_TITULO, Y_TITULO);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle(GRAFICO_TITULO);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private XYDataset createDataset(final List<Punto2D> mejoresDesempenios, final List<Punto2D> peoresDesempenios, final List<Punto2D> desempenioPromedio) {

        XYSeries mejores = new XYSeries(MEJOR_DESEMPENIO_TITULO);
        XYSeries peores = new XYSeries(PEOR_DESEMPENIO_TITULO);
        XYSeries promedios = new XYSeries(DESEMPENIO_PROMEDIO_TITULO);

        for (Punto2D punto2D : mejoresDesempenios) {
            mejores.add(punto2D.getX(), punto2D.getY());
        }
        for (Punto2D punto2D : peoresDesempenios) {
            peores.add(punto2D.getX(), punto2D.getY());
        }
        for (Punto2D punto2D : desempenioPromedio) {
            promedios.add(punto2D.getX(), punto2D.getY());
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(mejores);
        dataset.addSeries(peores);
        dataset.addSeries(promedios);

        return dataset;
    }

    private JFreeChart createChart(final XYDataset dataset, final String titulo, final String x, final String y) {

        JFreeChart chart = ChartFactory.createXYLineChart(titulo, x, y, dataset, PlotOrientation.VERTICAL, true, true
                , false);

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle(titulo, new Font("Serif", java.awt.Font.BOLD, 18)));

        return chart;
    }

    public static void mostrarGraficoMejoresDesempenios(LinkedList<Punto2D> mejoresDesempenios, LinkedList<Punto2D> peoresDesempenios, LinkedList<Punto2D> promedioDesempenio) {
        SwingUtilities.invokeLater(() -> {
            Graficador graficador = new Graficador();
            graficador.initUI(mejoresDesempenios, peoresDesempenios, promedioDesempenio);
            graficador.setVisible(true);
        });
    }
}
