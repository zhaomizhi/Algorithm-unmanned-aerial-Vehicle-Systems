/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicle.app;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import vehicle.Algorith.GraphAlgorithm;
import vehicle.model.Cargo;
import vehicle.model.ConfigureMap;
import vehicle.model.Graph;
import vehicle.model.Vertex;
import vehicle.model.WeatherArea;
import vehicle.model.WorkQueue;
import vehicle.model.WorkRequest;
import vehicle.model.helperClass.Calculator;
import vehicle.model.helperClass.CargoName;
import vehicle.model.helperClass.CityName;
import vehicle.model.helperClass.Color;

import vehicle.model.helperClass.WeatherStates;

/**
 *
 * @author Aki
 */
public class FlightInformation extends javax.swing.JFrame {

    private javax.swing.JComboBox citynamejComboBox2;
    private javax.swing.JComboBox deliverjComboBox2;

    private javax.swing.JLabel jLabel1ny;
    private javax.swing.JLabel jLabel2cityname;
    private javax.swing.JLabel jLabel3delivery;
    private javax.swing.JLabel jLabel4wo;
    private javax.swing.JLabel jLabel5ph;
    private javax.swing.JLabel jLabel6ba;
    private javax.swing.JLabel jLabel7de;
    private javax.swing.JLabel jLabel8wa;
    private javax.swing.JLabel jLabel9bo;
    private javax.swing.JLabel jLabel10an;

    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton startjButton1;
    private javax.swing.JButton submitjButton2;
    private javax.swing.JButton stopjButton;
    private javax.swing.JTextField qunfield;
     private javax.swing.JButton refreshbutton;

    private javax.swing.JLabel jLabel9qun;

    private javax.swing.JScrollPane cargojScrollPane1;
    private javax.swing.JTable cargojTable1;
    private javax.swing.JComboBox cargoquantityjComboBox2;

    Icon nyc = new ImageIcon("image/nyc.png");
    Icon boston = new ImageIcon("image/boston.png");
    Icon bal = new ImageIcon("image/bal.png");
    Icon dc = new ImageIcon("image/dc.png");
    Icon wo = new ImageIcon("image/wo.png");
    Icon phi = new ImageIcon("image/phi.png");
    Icon dov = new ImageIcon("image/dover.png");
    Icon an = new ImageIcon("image/an.png");

    Timer populateWeightTimer;

    //update flight 
    Timer flightMoveTimer;

    //change city's weather
    Timer weatherChangeTimer;

    //the supply states will be refreshed when a request is send
    Timer supplyStateTimer;

    //the table of the flight states will be refreshed once a second
    Timer populateTableTimer;

    Graph citymap;

    Cargo cargo;

    Vertex desinationCity;
    String desinationCityname;
    String cargoname;

    GraphAlgorithm ga;
    Vertex greateSupplyCity;
    WorkQueue workQueue = new WorkQueue();

    Vertex currentLocation;
    double[] currentLocationpoint = new double[2];
    double[] priviousLocationPoint = new double[2];

    Vertex startLocation;
    Vertex endLocation;

    Calculator calculator = new Calculator();

    private void resetGraph(Graph graph) {
        for (Vertex v : graph.getVertexList()) {
            v.setColor(Color.WHITE);
            v.setValue(Integer.MAX_VALUE);
        }
        graph.setGreateQun(0);

    }

    private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        for (WorkRequest w : workQueue.getWorkqueue()) {
            Object[] row = new Object[4];
            row[0] = w.getDestiinationVertex().getCityName();
            row[1] = w.getDeliveryname();
            row[2] = w.getPickupCargoCity().getCityName();
            row[3] = w.getState();
            model.addRow(row);
        }
    }

    private void populateCombox() {
        citynamejComboBox2.removeAllItems();
        deliverjComboBox2.removeAllItems();

        citynamejComboBox2.addItem(CityName.BALTIMORE);
        citynamejComboBox2.addItem(CityName.BOSTON);
        citynamejComboBox2.addItem(CityName.DOVER);
        citynamejComboBox2.addItem(CityName.NEW_YORK_CITY);
        citynamejComboBox2.addItem(CityName.PHILADELPHIA);
        citynamejComboBox2.addItem(CityName.WASHINGTON);
        citynamejComboBox2.addItem(CityName.WORCESTER);
        citynamejComboBox2.addItem(CityName.ANNAPOLIS);

        deliverjComboBox2.addItem(CargoName.BLOOD_SUPPLY);
        deliverjComboBox2.addItem(CargoName.MEDCINE_SUPPLY);
        deliverjComboBox2.addItem(CargoName.MEDICAL_FACILITIES);

        cargoquantityjComboBox2.addItem(CargoName.BLOOD_SUPPLY);
        cargoquantityjComboBox2.addItem(CargoName.MEDCINE_SUPPLY);
        cargoquantityjComboBox2.addItem(CargoName.MEDICAL_FACILITIES);
    }

    public void populateWeather() {

        for (Vertex v : citymap.getVertexList()) {
            double randomnumber = Math.random();
            if (randomnumber < 0.5) {
                v.getWeatherReport().setState(WeatherStates.CLEAR);
            } else if (randomnumber >= 0.5 && randomnumber < 0.85) {
                v.getWeatherReport().setState(WeatherStates.RAINY);
            } else {
                v.getWeatherReport().setState(WeatherStates.THUNDERSTORM);
            }
        }
    }

    public void populateWeatherinLine() {

        for (WeatherArea wa : citymap.getWeatherAreas()) {
            double randomnumber = Math.random();
            if (randomnumber < 0.01) {
                wa.setState(WeatherStates.CLEAR);
            } else {
                wa.setState(WeatherStates.THUNDERSTORM);
            }
        }
    }

    private void populateWeatherInPanel(Graph ga) {

        Graphics g = jPanel1.getGraphics();
        Image image;

        for (Vertex v : ga.getVertexList()) {
            if (v.getWeatherReport().getState().equals(WeatherStates.THUNDERSTORM)) {
                ImageIcon imageIcon = new ImageIcon("image/lighting.png");
                image = imageIcon.getImage();
                g.drawImage(image, (int) v.getXpoint(), (int) v.getYpoint() - 5, rootPane);
            } else {

                ImageIcon imageIcon = new ImageIcon("image/sun.png");
                image = imageIcon.getImage();
                g.drawImage(image, (int) v.getXpoint(), (int) v.getYpoint() - 5, rootPane);
            }

        }

    }

    private void populateQuntityTable(String cargoname) {
        DefaultTableModel model = (DefaultTableModel) cargojTable1.getModel();
        model.setRowCount(0);

        try {

            for (Vertex v : citymap.getVertexList()) {
                for (Cargo cargo : v.getCargos()) {
                    if (cargo.getName().equals(cargoname)) {

                        Object[] row = new Object[3];
                        row[0] = v.getCityName();
                        row[1] = cargo.getName();
                        row[2] = cargo.getQuantity();
                        model.addRow(row);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("exception initilization");
        }

    }

    private void changeWeight(Graph citymap){
        calculator.changeWeight(citymap);
    }

    public void goforCargo() {
        ga.DFSGreatestSupply(citymap, cargoname);
        greateSupplyCity = citymap.getGreateSupplyCity();
        // System.out.println(citymap.getGreateSupplyCity().getCityName());
    }

    private boolean isInthePathVertex(ArrayList<Vertex> path, double[] currentLocationpoint) {
        for (Vertex v : path) {
            if (currentLocationpoint[0] == v.getXpoint() && currentLocationpoint[1] == v.getYpoint()) {
                return true;
            }
        }
        return false;
    }

    public boolean isNextAvaiable(Vertex currentLocation) {
        if (currentLocation.getWeatherReport().getState().equals(WeatherStates.THUNDERSTORM)) {
            return false;
        } else {
            return true;
        }
    }

    public Vertex getTheNextCityVertex(ArrayList<Vertex> path, Vertex currentVertex) {
        int i = 0;
        for (Vertex vertex : path) {
            if (vertex == currentVertex) {
                i = path.indexOf(vertex);
            }
        }
        return path.get(i - 1);

    }

    private Vertex getTheNextCityVertex(ArrayList<Vertex> path, double[] currentLocationpoint) {
        int i = 0;
        for (Vertex v : path) {
            if (v.getXpoint() == currentLocationpoint[0] && v.getYpoint() == currentLocationpoint[1]) {
                i = path.indexOf(v);
            }
        }
        return path.get(i - 1);
    }

    private boolean currentLocationIsNotDestination(double[] currentLocationpoint, WorkRequest workRequest) {
        if (currentLocationpoint[0] != workRequest.getDestiinationVertex().getXpoint()
                && currentLocationpoint[1] != workRequest.getDestiinationVertex().getYpoint()) {
            return true;
        }
        return false;
    }

    private double[] calculateMovingLength(double[] currentLocationpoint, Vertex nextCurretnCityVertex) {
        double[] len = new double[2];
        len[0] = (nextCurretnCityVertex.getXpoint() - currentLocationpoint[0]) * 0.1;
        len[1] = (nextCurretnCityVertex.getYpoint() - currentLocationpoint[1]) * 0.1;
        return len;
    }

    private void savePriviousPoint(double[] currentLocationpoint, WorkRequest workRequest) {
        double x = currentLocationpoint[0];
        double y = currentLocationpoint[1];
        workRequest.setPriviousPoint(x, y);
    }

    private void refreshCargoQun(WorkRequest workRequest) {
        if (workRequest.isVisited()) {
            return;
        } else {
            for (Vertex v : workRequest.getPath()) {
                if (v.getCityName().equals(workRequest.getDestinationCityName())) {
                    for (Cargo cargo : v.getCargos()) {
                        if (cargo.getName().equals(workRequest.getDeliveryname())) {
                            cargo.setQuantity(cargo.getQuantity() + workRequest.getCargo().getQuantity());
                        }
                    }
                } else if (v.getCityName().equals(workRequest.getPickupCargoCity().getCityName())) {
                    for (Cargo cargo : v.getCargos()) {
                        if (cargo.getName().equals(workRequest.getDeliveryname())) {
                            cargo.setQuantity(cargo.getQuantity() - workRequest.getCargo().getQuantity());
                        }
                    }
                }
            }
        }
        workRequest.setVisited(true);

    }

    private void drawFlightLine() {
        Graphics g = jPanel1.getGraphics();
        g.setColor(java.awt.Color.GRAY);
        ((Graphics2D) g).setStroke(new BasicStroke(2));

        for (WorkRequest w : workQueue.getWorkqueue()) {
            g.drawLine((int) w.getPriviousLocationPoint()[0] + 5, (int) w.getPriviousLocationPoint()[1] + 60,
                    (int) w.getCurrentCitypoints()[0] + 5, (int) w.getCurrentCitypoints()[1] + 60);
        }
    }

    private boolean isQuantityEnough(String cargoname, int qun, Graph citymap) {
        for (Vertex v : citymap.getVertexList()) {
            for (Cargo cargo : v.getCargos()) {
                if (cargo.getName().equals(cargoname)) {
                    if (cargo.getQuantity() > qun) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isTheLineAvaiable(double[] currentpoint, Vertex nextpoint, WorkRequest wr) {
        for (WeatherArea wa : citymap.getWeatherAreas()) {
            if (wa.getState().equals(WeatherStates.THUNDERSTORM)) {
                if (calculator.perpendiculaDdistance(currentpoint[0], nextpoint.getXpoint(), currentpoint[1], nextpoint.getYpoint(), wa) < wa.getR()) {
                    wr.getWas().add(wa);
                    return false;
                }
            }
        }
        return true;
    }

    private void moveFlight(double[] currentLocationpoint, double[] movingLenghts) {
        double x = currentLocationpoint[0];
        double y = currentLocationpoint[1];
        currentLocationpoint[0] = (x + movingLenghts[0]);
        currentLocationpoint[1] = (y + movingLenghts[1]);
    }

    private void moveFlightAvoidThunder(double[] currentLocationpoint, WorkRequest workRequest, Vertex nextVertex) {
        //double[] startpoints = {currentLocationpoint[0], currentLocationpoint[1]};
        double[] endpoints = {nextVertex.getXpoint(), nextVertex.getYpoint()};
        WeatherArea wa = workRequest.getWas().get(0);

        double[] midpoints = calculator.givenLengthpoint(workRequest.getStartpoints(), endpoints, wa);
        //double[] lengthstartToMid = calculator.startToMid(startpoints, midpoints);
        double[] lengthMidToEnd = calculator.avoidMovingLength(midpoints, endpoints);
        double[] lengthStartToMid = calculator.avoidMovingLength(workRequest.getStartpoints(), midpoints);
        
        if(currentLocationpoint[0] == workRequest.getStartpoints()[0]){
             currentLocationpoint[0] = currentLocationpoint[0] + lengthStartToMid[0];
            currentLocationpoint[1] = currentLocationpoint[1] + lengthStartToMid[1];
        }else if(currentLocationpoint[0] == midpoints[0]){
              currentLocationpoint[0] = currentLocationpoint[0] + lengthMidToEnd[0];
            currentLocationpoint[1] = currentLocationpoint[1] + lengthMidToEnd[1];
        }else if((workRequest.getStartpoints()[0] < currentLocationpoint[0] && currentLocationpoint[0] < midpoints[0])
                || (midpoints[0] < currentLocationpoint[0] && currentLocationpoint[0] < workRequest.getStartpoints()[0])){
            currentLocationpoint[0] = currentLocationpoint[0] + lengthStartToMid[0];
            currentLocationpoint[1] = currentLocationpoint[1] + lengthStartToMid[1];
        }else {            
            currentLocationpoint[0] = currentLocationpoint[0] + lengthMidToEnd[0];
            currentLocationpoint[1] = currentLocationpoint[1] + lengthMidToEnd[1];
        }
           
    }

    public void updateFlight() {
        for (WorkRequest workRequest : workQueue.getWorkqueue()) {
            ArrayList<Vertex> path = workRequest.getPath();

            currentLocationpoint = workRequest.getCurrentCitypoints();
            //the current location is not the desination location

            if (currentLocationIsNotDestination(currentLocationpoint, workRequest)) {
                //determain whether the curretnlocation is the VERTEX location in the path               
                //if the current location is in the city vertex
                if (isInthePathVertex(path, currentLocationpoint)) {
                    //avaiable
                    Vertex nextCurretnCityVertex = getTheNextCityVertex(path, currentLocationpoint);
                    workRequest.setNextVertex(nextCurretnCityVertex);
                    if (isNextAvaiable(nextCurretnCityVertex)) {
                        if (isTheLineAvaiable(currentLocationpoint, nextCurretnCityVertex, workRequest)) {
                            // double k = calculatek(currentLocation.getXpoint(), currentLocation.getYpoint(), nextCurretnCityVertex.getXpoint(), nextCurretnCityVertex.getYpoint());
                            //workRequest.setK(k);//need k here 
                            double[] movingLenghts = calculateMovingLength(currentLocationpoint, nextCurretnCityVertex);
                            workRequest.setLengths(movingLenghts);
                            savePriviousPoint(currentLocationpoint, workRequest);
                            moveFlight(currentLocationpoint, movingLenghts);
                            double[] startpoints = new double[2];
                            startpoints[0] = currentLocationpoint[0];
                            startpoints[1] = currentLocationpoint[1];
                            workRequest.setStartpoints(startpoints);
                            System.out.println("vertex");
                            System.out.println("x" + currentLocationpoint[0] + "y " + currentLocationpoint[1]);
                            workRequest.setState("The flight is in good condition, flying towards " + nextCurretnCityVertex.getCityName());

                        } else {
                            double[] startpoints = new double[2];
                            startpoints[0] = currentLocationpoint[0];
                            startpoints[1] = currentLocationpoint[1];
                            workRequest.setStartpoints(startpoints);
                            savePriviousPoint(currentLocationpoint, workRequest);
                            moveFlightAvoidThunder(currentLocationpoint, workRequest, nextCurretnCityVertex);
                            workRequest.setState("avoid the bad weather in path");
                            System.out.println("The flight has encounter avoid path");
                        }
//is not avaiable
                    } else {
                        workRequest.setState("Delayed due to weather. Current location is " + ga.searchForVertex(citymap, currentLocationpoint).getCityName());
                    }
                    //the current location is in the line 
                } else {
                    if (workRequest.getState().equals("avoid the bad weather in path")) {
                        savePriviousPoint(currentLocationpoint, workRequest);
                        moveFlightAvoidThunder(currentLocationpoint, workRequest, workRequest.getNextVertex());
                        System.out.println("The flight has encounter avoid path");
                    } else {
                        savePriviousPoint(currentLocationpoint, workRequest);
                        moveFlight(currentLocationpoint, workRequest.getLengths());
                        System.out.println("run the line");
                        System.out.println("x" + currentLocationpoint[0] + "y " + currentLocationpoint[1]);
                    }
                }
            } else {
                workRequest.setState("The flight has arrived the destination :" + workRequest.getDestinationCityName());
                //destinatin cargo
                refreshCargoQun(workRequest);
            }
        }
    }

    public FlightInformation() {
        initComponents();
        ga = new GraphAlgorithm();
        citymap = new Graph(8);
        populateCombox();
        ConfigureMap.configure(citymap);
        setSize(1250, 900);
        setResizable(false);

        flightMoveTimer = new Timer(500, flightMoveListener);
        populateTableTimer = new Timer(3000, populatetableListener);
        weatherChangeTimer = new Timer(1500, populateWeather);
        populateWeightTimer = new Timer(5000,populateWeightListener);
        // refreshTimer = new Timer(0,refreshListen);

    }

    private final ActionListener populatetableListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            populateTable();
            populateQuntityTable((String) cargoquantityjComboBox2.getSelectedItem());
        }
    };

    private final ActionListener populateWeather = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            populateWeather();
            populateWeatherinLine();
            populateWeatherInPanel(citymap);
  
        }

    };
    

    private final ActionListener populateWeightListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
           changeWeight(citymap);
        }  
    };

    private final ActionListener flightMoveListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateFlight();
            drawFlightLine();
        }
    };

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        refreshbutton = new JButton();
        startjButton1 = new javax.swing.JButton();
        jLabel2cityname = new javax.swing.JLabel();
        citynamejComboBox2 = new javax.swing.JComboBox();
        deliverjComboBox2 = new javax.swing.JComboBox();
        jLabel3delivery = new javax.swing.JLabel();
        jLabel1ny = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4wo = new javax.swing.JLabel();
        jLabel5ph = new javax.swing.JLabel();
        jLabel6ba = new javax.swing.JLabel();
        jLabel7de = new javax.swing.JLabel();
        jLabel8wa = new javax.swing.JLabel();
        jLabel9bo = new javax.swing.JLabel();
        jLabel10an = new javax.swing.JLabel();
        cargoquantityjComboBox2 = new JComboBox();

        JLabel titlejLabel9 = new JLabel();
        titlejLabel9.setText("Flight Monitoring Panel");
        titlejLabel9.setFont(new Font("Serif", Font.PLAIN, 20));
        titlejLabel9.setBounds(20, 20, 300, 30);
        jPanel1.add(titlejLabel9);

        submitjButton2 = new javax.swing.JButton();
        stopjButton = new javax.swing.JButton();

        qunfield = new javax.swing.JTextField();
        jLabel9qun = new JLabel();

        cargojScrollPane1 = new JScrollPane();
        cargojTable1 = new JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jLabel9qun.setText("Qun:");
        jLabel9qun.setBounds(40, 750, 60, 30);
        jPanel1.add(jLabel9qun);

        qunfield.setBounds(70, 750, 60, 30);
        jPanel1.add(qunfield);

        startjButton1.setText("Start");

        jLabel2cityname.setText("City Name");
        jLabel2cityname.setBounds(140, 750, 60, 30);
        jPanel1.add(jLabel2cityname);

        citynamejComboBox2.setBounds(200, 750, 110, 30);
        jPanel1.add(citynamejComboBox2);

        jLabel3delivery.setText("Delivery");
        jLabel3delivery.setBounds(355, 750, 60, 30);
        jPanel1.add(jLabel3delivery);

        deliverjComboBox2.setBounds(415, 750, 110, 30);
        jPanel1.add(deliverjComboBox2);

        stopjButton.setBounds(760, 750, 70, 30);
        stopjButton.setText("STOP");
        stopjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopjButton1ActionPerformed(evt);
            }

        });

        jPanel1.add(stopjButton);

        submitjButton2.setBounds(560, 750, 70, 30);
        submitjButton2.setText("Submit");

        startjButton1.setBounds(660, 750, 70, 30);
        startjButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startjButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(startjButton1);
        
        
        refreshbutton.setText("Refresh Panel");
        refreshbutton.setBounds(880, 750, 150, 30);
        refreshbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              refreshjButton1ActionPerformed(e);
            }
        }
        );
       // jPanel1.add(refreshbutton);


        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "Destination", "Delivery", "Pick up delivery city", "Status"
                }
        ));

        jScrollPane1.setViewportView(jTable1);

        jScrollPane1.setBounds(80, 500, 1000, 200);

        jPanel1.add(jScrollPane1);

        cargojTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null}
                },
                new String[]{
                    "City", "Delivery name", "Quantity"
                }
        ));

        cargojScrollPane1.setViewportView(cargojTable1);
        cargojScrollPane1.setBounds(900, 150, 320, 200);
        jPanel1.add(cargojScrollPane1);

        cargoquantityjComboBox2.setBounds(900, 100, 200, 30);
        jPanel1.add(cargoquantityjComboBox2);
        cargoquantityjComboBox2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cargoQuantityComboxActionPerformed(e);
            }

        });

        jLabel1ny.setText(CityName.NEW_YORK_CITY);
        jLabel1ny.setBounds(400, 30, 200, 100);
        jLabel1ny.setIcon(nyc);
        jPanel1.add(jLabel1ny);

        jLabel4wo.setText(CityName.WORCESTER);
        jLabel4wo.setBounds(100, 130, 200, 100);
        jLabel4wo.setIcon(wo);
        jPanel1.add(jLabel4wo);

        jLabel5ph.setText(CityName.PHILADELPHIA);
        jLabel5ph.setBounds(520, 110, 200, 100);
        jLabel5ph.setIcon(phi);
        jPanel1.add(jLabel5ph);

        jLabel6ba.setText(CityName.BALTIMORE);
        jLabel6ba.setBounds(80, 340, 200, 100);
        jLabel6ba.setIcon(bal);
        jPanel1.add(jLabel6ba);

        jLabel7de.setText(CityName.DOVER);
        jLabel7de.setIcon(dov);
        jLabel7de.setBounds(700, 400, 200, 100);
        jPanel1.add(jLabel7de);

        jLabel8wa.setText(CityName.WASHINGTON);
        jLabel8wa.setIcon(dc);
        jLabel8wa.setBounds(580, 200, 200, 100);
        jPanel1.add(jLabel8wa);

        jLabel9bo.setText(CityName.BOSTON);
        jLabel9bo.setIcon(boston);
        jLabel9bo.setBounds(250, 170, 200, 100);
        jPanel1.add(jLabel9bo);

        jLabel10an.setText(CityName.ANNAPOLIS);
        jLabel10an.setIcon(an);
        jLabel10an.setBounds(420, 360, 200, 100);
        jPanel1.add(jLabel10an);

        submitjButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitjButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(submitjButton2);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
        pack();

    }

    private void startjButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        weatherChangeTimer.start();
        flightMoveTimer.start();
        populateTableTimer.start();
        populateWeightTimer.start();

    }
    
    private void refreshjButton1ActionPerformed(ActionEvent evt){
        Graphics g = jPanel1.getGraphics();
        g.clearRect(100, 100, 700, 700);
    }

    private void stopjButton1ActionPerformed(ActionEvent evt) {
        weatherChangeTimer.stop();
        populateTableTimer.stop();
        flightMoveTimer.stop();
        populateWeightTimer.stop();
    }

    private void submitjButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        try {
            int qun = Integer.parseInt(qunfield.getText());

            cargoname = (String) deliverjComboBox2.getSelectedItem();
            desinationCityname = (String) citynamejComboBox2.getSelectedItem();

            if (isQuantityEnough(cargoname, qun, citymap)) {
                Cargo cargo = new Cargo(cargoname, qun);
                goforCargo();
                Vertex destinationCity = ga.searchForVertex(citymap, desinationCityname);
                ArrayList<Vertex> wholepath = ga.ShorttestPath(citymap, greateSupplyCity);
                ArrayList<Vertex> certainpaht = ga.findCertainPath(wholepath, desinationCityname, greateSupplyCity.getCityName());
                WorkRequest wq = new WorkRequest(destinationCity, cargoname, greateSupplyCity, certainpaht, cargo);
                wq.setDestinationCityName(desinationCityname);
                workQueue.getWorkqueue().add(wq);
                populateTable();
                resetGraph(citymap);
            } else {
                JOptionPane.showMessageDialog(null, "There is not enough cargo to deliver");
            }

        } catch (java.lang.NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Sorry, please type the right quantity");
        }

    }

    private void cargoQuantityComboxActionPerformed(ActionEvent e) {
        populateQuntityTable((String) cargoquantityjComboBox2.getSelectedItem());
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FlightInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FlightInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FlightInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FlightInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FlightInformation().setVisible(true);
            }
        });
    }

}
