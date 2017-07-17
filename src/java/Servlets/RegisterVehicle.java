package Servlets;

import Controller.Vehicle;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterVehicle extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String chasisNo = request.getParameter("chasisNo");
            String engineNo = request.getParameter("engineNo");
            String vehicleNo = request.getParameter("vehicleNo");
            String color = request.getParameter("color");
            String model = request.getParameter("model");
            String brand = request.getParameter("brand");
            String owner = request.getParameter("owner");
            String type = request.getParameter("type");
            int year = Integer.parseInt(request.getParameter("year"));
            
            String amount = request.getParameter("amount");
            String expireDate = request.getParameter("expireDate");
            String insureDate = request.getParameter("insuredDate");
            int insuranceType = Integer.parseInt(request.getParameter("insuranceType"));
            int period = Integer.parseInt(request.getParameter("period"));
            String periodType = request.getParameter("periodType");

            int res = new Vehicle().registerVehicle(chasisNo, engineNo, vehicleNo, color, model, brand, owner, type, year, amount, expireDate, insureDate, insuranceType, periodType, period);
            if (res == Vehicle.OK) {
                out.write("OK");
            } else {
                out.write("ERROR");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.write("EMPTY");
        }
    }

}
