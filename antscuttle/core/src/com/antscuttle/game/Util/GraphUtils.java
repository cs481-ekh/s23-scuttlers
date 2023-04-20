
package com.antscuttle.game.Util;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import org.jgrapht.Graph;
import org.jgrapht.generate.EmptyGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

/**
 *
 * @author antho
 */
public class GraphUtils {
    // Gets the vertex name associated with point x,y (0-22,0-26)
    public static String getVertexName(int x, int y){
        String xstr = (x<10) ? "0"+x : ""+x;
        String ystr = (y<10) ? "0"+y : ""+y;
        return "v"+xstr+ystr;
    }
    
    public static Graph<String,DefaultEdge> getFreshSimpleGraph(){
         // Vertices will be named for tile placement:
         // vXXYY, where 00 <= XX <= 26 amd 00 <= YY <= 22
        Supplier<String> vSupplier = new Supplier<String>()
        {
            private int i = -1;
            private int j = 0;
            
            @Override
            public String get()
            {
                 String name = "v" + ++i +""+ j;
                if(i<10){
                    name="v0"+name.substring(1);
                }
                if(j<10){
                    name=name.substring(0,3)+"0"+name.substring(3);
                }
                if(i == 26){
                    i = -1;
                    j++;
                } 

                return name;
            }
        };

        Graph<String, DefaultEdge> graph =
            new SimpleGraph<>(vSupplier, SupplierUtil.createDefaultEdgeSupplier(), false);

        EmptyGraphGenerator<String, DefaultEdge> emptyGenerator =
            new EmptyGraphGenerator<>(23*27);

        emptyGenerator.generateGraph(graph);
        
        // Create edges for an ortho graph layout
        for(int y=0; y< 23; y++){
            for(int x=0; x<27; x++){
                String xstr = (x<10) ? "0"+x : ""+x;
                String ystr = (y<10) ? "0"+y : ""+y;
                String vertex = "v"+xstr+ystr;
                
                String xstrR = (x<9)? "0" + (x+1) : ""+(x+1);
                String vR = "v" + xstrR + ystr;
                
                String ystrU = (y<9)? "0"+(y+1) : ""+(y+1);
                String vU = "v"+xstr+ystrU;
                
                if(x<26){
                    graph.addEdge(vertex, vR);
                }
                if(y<22){
                    graph.addEdge(vertex, vU);
                }
            }
        }
        return graph;
    }

    public static void removeFromGraph(Graph<String,DefaultEdge> g, int x, int y) {
        String vertex = getVertexName(x,y);
        if(g.containsVertex(vertex))
            g.removeVertex(vertex);
    }
    
    public static void addToGraph(Graph<String,DefaultEdge> g, int x, int y){
        String vertex = getVertexName(x,y);
        Set<String> neighbors = getVertexNeighbors(x,y);
        
        g.addVertex(vertex);
        for(String n : neighbors){
            if(g.containsVertex(vertex) && g.containsVertex(n)){
                g.addEdge(vertex, n);
            }
        }
        
    }
    
    public static Set<String> getVertexNeighbors(int x, int y){
        Set<String> neighbors = new HashSet<>();
        
        String xstr = (x<10) ? "0"+x : ""+x;
        String ystr = (y<10) ? "0"+y : ""+y;
        // R neighbor
        if(x < 26){
            String xstrR = (x<9) ? "0" + (x+1) : ""+(x+1);
            String vR = "v" + xstrR + ystr;
            neighbors.add(vR);
        }
        // U neighbor
        if(y < 22){
            String ystrU = (y<9)? "0"+(y+1) : ""+(y+1);
            String vU = "v"+xstr+ystrU;
            neighbors.add(vU);
        }
        // L neighbor
        if(x > 0){
            String xstrL = (x<11) ? "0" + (x-1) : "" + (x-1);
            String vL = "v" + xstrL + ystr;
            neighbors.add(vL);
        }
        // D neighbor
        if(y > 0){
            String ystrD = (y<11) ? "0" + (y-1) : "" + (y-1);
            String vD = "v" + xstr + ystrD;
            neighbors.add(vD);
        }
        return neighbors;
    }
    
    public static Set<Point> getVertexNeighborsAsPoints(int x, int y){
        Set<String> neighbors = getVertexNeighbors(x,y);
        Set<Point> points = new HashSet<>();
        for(String vname: neighbors){
            int vx = Integer.parseInt(vname.substring(1, 3));
            int vy = Integer.parseInt(vname.substring(3, 5));
            points.add(new Point(vx, vy));
        }
        return points;
    }
}
