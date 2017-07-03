package com.wayfinder.rinno.wayfinderentregafinal;

import android.app.Application;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.Crashlytics;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wayfinder.rinno.wayfinderentregafinal.algoritmo.Graph;
import com.wayfinder.rinno.wayfinderentregafinal.model.Alimentacion;
import com.wayfinder.rinno.wayfinderentregafinal.model.Descuentos;
import com.wayfinder.rinno.wayfinderentregafinal.model.Edges;
import com.wayfinder.rinno.wayfinderentregafinal.model.Globales;
import com.wayfinder.rinno.wayfinderentregafinal.model.Nodes;
import com.wayfinder.rinno.wayfinderentregafinal.model.Stores;

import io.fabric.sdk.android.Fabric;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Rinno on 27-06-2017.
 */

public class FirebaseApplication extends Application {

    DatabaseReference referenceApumanque;
    DatabaseReference referenceNodes;
    DatabaseReference referenceEdges;
    DatabaseReference referenceStores;
    DatabaseReference referenceCategories;
    DatabaseReference referenceFoodCategories;
    DatabaseReference referenceDiscounts;

    public Map<String, Stores> arrStores = new TreeMap<String, Stores>();
    public Map<String, Stores> arrCategories = new TreeMap<String, Stores>();
    public Map<String, Alimentacion> arrFoodCategories = new TreeMap<String, Alimentacion>();

    public ArrayList<Stores> messages = new ArrayList<Stores>();
    public ArrayList<Alimentacion> arrAlimentacion = new ArrayList<Alimentacion>();
    public ArrayList<Stores> categories = new ArrayList<Stores>();
    public ArrayList<Stores> categoriesBotones = new ArrayList<Stores>();
    public ArrayList<Descuentos> arrImgDescuentos = new ArrayList<Descuentos>();
    public ArrayList<String> arrCategoriesFb = new ArrayList<String>();
    public ArrayList<Descuentos> arrDescuentos = new ArrayList<Descuentos>();
    public ArrayList<Descuentos> arrDescuentosBotones = new ArrayList<Descuentos>();

    public String imgParticipaWayfinder;

    public ArrayList arregloIdRuta = new ArrayList();
    public ArrayList arregloLocationX = new ArrayList();
    public ArrayList arregloLocationY = new ArrayList();
    public ArrayList arregloType = new ArrayList();
    public ArrayList arregloFloor = new ArrayList();

    public Map<String, Nodes> coordRect = new HashMap<String, Nodes>();
    public List<Graph.Vertex<String>> vertices = new ArrayList<Graph.Vertex<String>>();

    public ArrayList arregloA = new ArrayList();
    public ArrayList arregloB = new ArrayList();
    public ArrayList arregloCosto = new ArrayList();

    float locationXA = 0;
    float locationXB = 0;
    float locationYA = 0;
    float locationYB = 0;


    public ArrayList arregloLocationZ = new ArrayList();

    float locationZA = 0;
    float locationZB = 0;


    public FirebaseApplication() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        referenceApumanque = database.getReference().child("Apumanque");
        referenceNodes = database.getReference().child("Nodes");
        referenceEdges = database.getReference().child("Edges");
        referenceStores = database.getReference().child("Stores");
        referenceCategories = database.getReference().child("Categories");
        referenceFoodCategories = database.getReference().child("FoodCategories");
        referenceDiscounts = database.getReference().child("Discounts");

        referenceNodes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (int i = 1; i <= dataSnapshot.getChildrenCount(); i++) {

                    Nodes nod = dataSnapshot.child(String.valueOf(i)).getValue(Nodes.class);
                    Graph.Vertex<String> a = new Graph.Vertex<String>(nod.getId());
                    arregloIdRuta.add(nod.getId());
                    //Log.d(nod.getId(), nod.getLocationX() + "- " + nod.getLocationY());
                    arregloLocationX.add(Float.parseFloat(String.valueOf((nod.getLocationX() * Globales.ancho) + 5)));
                    arregloLocationY.add(Float.parseFloat(String.valueOf((nod.getLocationY() * Globales.alto) + 5)));
                    arregloLocationZ.add(Float.parseFloat(String.valueOf((nod.getLocationZ()))));

                    arregloType.add(String.valueOf(nod.getType()));


                    arregloFloor.add(String.valueOf(nod.getFloor()));

                    coordRect.put(nod.getId(), new Nodes(nod.getId(), nod.getRectX() * Globales.ancho, nod.getRectY() * Globales.ancho, ((nod.getRectX() * Globales.ancho) + (nod.getRectW() * Globales.ancho)), ((nod.getRectY() * Globales.ancho) + (nod.getRectH() * Globales.ancho)),
                            nod.getImg(), nod.getImgX() * Globales.ancho, nod.getImgY() * Globales.ancho, nod.getImgW(), nod.getImgH()));
                    vertices.add(a);
                }

                RGlobal.arregloLocationX = arregloLocationX;
                RGlobal.arregloLocationY = arregloLocationY;
                RGlobal.listaNodesID = arregloIdRuta;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", "ERROR: " + databaseError);
            }
        });
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /*
         *CONEXION DEL NODO (Edges) DESDE FIREBASE. CAPTURA DE DATOS A UTILIZAR POR LA APP
         */
        referenceEdges.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (int i = 1; i < dataSnapshot.getChildrenCount(); i++) {

                    Edges edg = dataSnapshot.child(String.valueOf(i)).getValue(Edges.class);
                    Graph.Vertex<String> a = new Graph.Vertex<String>(edg.getSource());
                    Graph.Vertex<String> b = new Graph.Vertex<String>(edg.getEnd());

                    for (int l = 0; l < vertices.size(); l++) {
                        if (vertices.get(l).equals(a)) {
                            arregloA.add(l);
                            locationXA = (float) arregloLocationX.get(l);
                            locationYA = (float) arregloLocationY.get(l);
                            if ((float) arregloLocationZ.get(l) == 2.0) {
                                locationZA = (float) arregloLocationZ.get(l) + 3;
                            } else if ((float) arregloLocationZ.get(l) == 3.0) {
                                locationZA = (float) arregloLocationZ.get(l) + 10;
                            } else {
                                locationZA = (float) arregloLocationZ.get(l);
                            }
                        }
                    }

                    for (int l = 0; l < vertices.size(); l++) {
                        if (vertices.get(l).equals(b)) {
                            arregloB.add(l);
                            locationXB = (float) arregloLocationX.get(l);
                            locationYB = (float) arregloLocationY.get(l);
                            locationZB = (float) arregloLocationZ.get(l);
                        }
                    }
                    double distance = Math.sqrt((locationXA - locationXB) * (locationXA - locationXB) + (locationYA - locationYB) * (locationYA - locationYB) + (locationZA - locationZB) * (locationZA - locationZB));
                    arregloCosto.add((int) distance);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", "ERROR: " + databaseError);
            }
        });


        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        RGlobal.tiendasPrimerPiso = new ArrayList<>();
        RGlobal.tiendasSegundoPiso = new ArrayList<>();
        RGlobal.tiendasTercerPiso = new ArrayList<>();

        referenceStores.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Stores> tiendas = new ArrayList<Stores>();
                for (int i = 0; i < dataSnapshot.getChildrenCount(); i++) {

                    Stores str = dataSnapshot.child(String.valueOf(i)).getValue(Stores.class);
                    arrStores.put(str.getName(), str);
                    messages.add(str);
                    tiendas.add(str);
                    switch (str.getFloor()) {
                        case 1:
                            RGlobal.tiendasPrimerPiso.add(str);
                            break;
                        case 2:

                            RGlobal.tiendasSegundoPiso.add(str);
                            break;
                        case 3:

                            RGlobal.tiendasTercerPiso.add(str);
                            break;


                    }
                }

                Collections.sort(tiendas, new Comparator<Stores>() {
                    @Override
                    public int compare(Stores o1, Stores o2) {
                        return StringUtils.stripAccents(o1.getName()).compareToIgnoreCase(StringUtils.stripAccents(o2.getName()));
                    }
                });

                Collections.sort(RGlobal.tiendasPrimerPiso, new Comparator<Stores>() {
                    @Override
                    public int compare(Stores o1, Stores o2) {
                        return StringUtils.stripAccents(o1.getName()).compareToIgnoreCase(StringUtils.stripAccents(o2.getName()));
                    }
                });

                Collections.sort(RGlobal.tiendasSegundoPiso, new Comparator<Stores>() {
                    @Override
                    public int compare(Stores o1, Stores o2) {
                        return StringUtils.stripAccents(o1.getName()).compareToIgnoreCase(StringUtils.stripAccents(o2.getName()));
                    }
                });
                Collections.sort(RGlobal.tiendasTercerPiso, new Comparator<Stores>() {
                    @Override
                    public int compare(Stores o1, Stores o2) {
                        return StringUtils.stripAccents(o1.getName()).compareToIgnoreCase(StringUtils.stripAccents(o2.getName()));
                    }
                });


                RGlobal.tiendas = tiendas;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", "ERROR: " + databaseError);
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////

         /*
         *CONEXION DEL NODO (Categories) DESDE FIREBASE. CAPTURA DE DATOS A UTILIZAR POR LA APP
         */

        categories.clear();
        referenceCategories.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String str = ds.getKey().toString();
                    arrCategoriesFb.add(str);
                    String str1 = (String) dataSnapshot.child(ds.getKey().toString()).child("nombre").getValue();
                    String str2 = ds.getKey();

                    categories.add(new Stores(str1, str2));

                    //Log.d("Category-Name", str);
                    //Log.d("Category-Value", str1);

                    categoriesBotones.add(new Stores(str, str1));
                }

                Collections.sort(categories, new Comparator<Stores>() {
                    @Override
                    public int compare(Stores o1, Stores o2) {
                        return o1.getName().compareToIgnoreCase(o2.getName());
                    }
                });
                Collections.sort(categoriesBotones, new Comparator<Stores>() {
                    @Override
                    public int compare(Stores o1, Stores o2) {
                        return o1.getName().compareToIgnoreCase(o2.getName());
                    }
                });
                RGlobal.categorias = categories;

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", "ERROR: " + databaseError);
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
          /*
         *CONEXION DEL NODO (FoodCategories) DESDE FIREBASE. CAPTURA DE DATOS A UTILIZAR POR LA APP
         */


        RGlobal.listaRestauran = new ArrayList<>();
        RGlobal.listaCafeteria = new ArrayList<>();
        RGlobal.listaChocolateria = new ArrayList<>();
        RGlobal.listaHeladeria = new ArrayList<>();

        referenceFoodCategories.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (int i = 0; i < dataSnapshot.getChildrenCount(); i++) {
                    Alimentacion alim = dataSnapshot.child(String.valueOf(i)).getValue(Alimentacion.class);
                    arrFoodCategories.put(String.valueOf(i), new Alimentacion(alim.getCafeterias(), alim.getChocolateriaYConfiteria(),
                            alim.getHeladerias(), alim.getNames(), alim.getRestoran()));

                    arrAlimentacion = new ArrayList<Alimentacion>();
                    for (Map.Entry<String, Alimentacion> arrFoodCat : arrFoodCategories.entrySet()) {
                        Alimentacion obje = new Alimentacion(arrFoodCat.getValue().getCafeterias(), arrFoodCat.getValue().getChocolateriaYConfiteria(),
                                arrFoodCat.getValue().getHeladerias(), arrFoodCat.getValue().getNames(), arrFoodCat.getValue().getRestoran());

                        arrAlimentacion.add(obje);
                    }


                }

                for (int i = 0; i < RGlobal.appF.arrAlimentacion.get(3).getRestoran().size(); i++) {
                    for (Map.Entry<String, Stores> arrTiendaStore : RGlobal.appF.arrStores.entrySet()) {
                        Stores obje = new Stores(arrTiendaStore.getValue().getCategory(), arrTiendaStore.getValue().getDescription(),
                                arrTiendaStore.getValue().getFloor(), arrTiendaStore.getValue().getLogo(), arrTiendaStore.getValue().getName(),
                                arrTiendaStore.getValue().getNodeID(), arrTiendaStore.getValue().getNodeIDForContextual(), arrTiendaStore.getValue().getPhone(),
                                arrTiendaStore.getValue().getRoad(), arrTiendaStore.getValue().getStoreImage(), arrTiendaStore.getValue().getStoreNumber(),
                                arrTiendaStore.getValue().getTags(), arrTiendaStore.getValue().getEmail(), arrTiendaStore.getValue().getWeb());
                        if (RGlobal.appF.arrAlimentacion.get(3).getRestoran().get(i).toString().equals(arrTiendaStore.getValue().getStoreNumber())) {
                            RGlobal.listaRestauran.add(obje);
                        }
                    }
                }

                for (int i = 0; i < RGlobal.appF.arrAlimentacion.get(0).getCafeterias().size(); i++) {
                    for (Map.Entry<String, Stores> arrTiendaStore : RGlobal.appF.arrStores.entrySet()) {
                        Stores obje = new Stores(arrTiendaStore.getValue().getCategory(), arrTiendaStore.getValue().getDescription(),
                                arrTiendaStore.getValue().getFloor(), arrTiendaStore.getValue().getLogo(), arrTiendaStore.getValue().getName(),
                                arrTiendaStore.getValue().getNodeID(), arrTiendaStore.getValue().getNodeIDForContextual(), arrTiendaStore.getValue().getPhone(),
                                arrTiendaStore.getValue().getRoad(), arrTiendaStore.getValue().getStoreImage(), arrTiendaStore.getValue().getStoreNumber(),
                                arrTiendaStore.getValue().getTags(), arrTiendaStore.getValue().getEmail(), arrTiendaStore.getValue().getWeb());
                        if (RGlobal.appF.arrAlimentacion.get(0).getCafeterias().get(i).toString().equals(arrTiendaStore.getValue().getStoreNumber())) {
                            RGlobal.listaCafeteria.add(obje);
                        }
                    }
                }

                for (int i = 0; i < RGlobal.appF.arrAlimentacion.get(1).getChocolateriaYConfiteria().size(); i++) {
                    for (Map.Entry<String, Stores> arrTiendaStore : RGlobal.appF.arrStores.entrySet()) {
                        Stores obje = new Stores(arrTiendaStore.getValue().getCategory(), arrTiendaStore.getValue().getDescription(),
                                arrTiendaStore.getValue().getFloor(), arrTiendaStore.getValue().getLogo(), arrTiendaStore.getValue().getName(),
                                arrTiendaStore.getValue().getNodeID(), arrTiendaStore.getValue().getNodeIDForContextual(), arrTiendaStore.getValue().getPhone(),
                                arrTiendaStore.getValue().getRoad(), arrTiendaStore.getValue().getStoreImage(), arrTiendaStore.getValue().getStoreNumber(),
                                arrTiendaStore.getValue().getTags(), arrTiendaStore.getValue().getEmail(), arrTiendaStore.getValue().getWeb());
                        if (RGlobal.appF.arrAlimentacion.get(1).getChocolateriaYConfiteria().get(i).toString().equals(arrTiendaStore.getValue().getStoreNumber())) {
                            RGlobal.listaChocolateria.add(obje);
                        }
                    }
                }


                for (int i = 0; i < RGlobal.appF.arrAlimentacion.get(2).getHeladerias().size(); i++) {
                    for (Map.Entry<String, Stores> arrTiendaStore : RGlobal.appF.arrStores.entrySet()) {
                        Stores obje = new Stores(arrTiendaStore.getValue().getCategory(), arrTiendaStore.getValue().getDescription(),
                                arrTiendaStore.getValue().getFloor(), arrTiendaStore.getValue().getLogo(), arrTiendaStore.getValue().getName(),
                                arrTiendaStore.getValue().getNodeID(), arrTiendaStore.getValue().getNodeIDForContextual(), arrTiendaStore.getValue().getPhone(),
                                arrTiendaStore.getValue().getRoad(), arrTiendaStore.getValue().getStoreImage(), arrTiendaStore.getValue().getStoreNumber(),
                                arrTiendaStore.getValue().getTags(), arrTiendaStore.getValue().getEmail(), arrTiendaStore.getValue().getWeb());
                        if (RGlobal.appF.arrAlimentacion.get(2).getHeladerias().get(i).toString().equals(arrTiendaStore.getValue().getStoreNumber())) {
                            RGlobal.listaHeladeria.add(obje);
                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", "ERROR: " + databaseError);
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /*
         *CONEXION DEL NODO (Apumanque) DESDE FIREBASE. CAPTURA DE DATOS A UTILIZAR POR LA APP
         */
        referenceApumanque.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                imgParticipaWayfinder = (String) dataSnapshot.child("campañaVigente").child("imgURLWayFinder").getValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", "ERROR: " + databaseError);
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        referenceDiscounts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Descuentos desc = ds.getValue(Descuentos.class);
                    if (!desc.getEstado().equals("0")) {
                        arrImgDescuentos.add(desc);
                        arrDescuentos.add(desc);
                        arrDescuentosBotones.add(desc);
                        RGlobal.listaImagenesDescuento.add(desc);
                        RGlobal.listaDescuentos.add(desc);
                        RGlobal.listaDescuentosCategoria.add(desc);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", "ERROR: " + databaseError);
            }
        });


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    public DatabaseReference getReferenceNodes() {
        return referenceNodes;
    }

    public void setReferenceNodes(DatabaseReference referenceNodes) {
        this.referenceNodes = referenceNodes;
    }

    public DatabaseReference getReferenceEdges() {
        return referenceEdges;
    }

    public void setReferenceEdges(DatabaseReference referenceEdges) {
        this.referenceEdges = referenceEdges;
    }

    public DatabaseReference getReferenceStores() {
        return referenceStores;
    }

    public void setReferenceStores(DatabaseReference referenceStores) {
        this.referenceStores = referenceStores;
    }

    public DatabaseReference getReferenceFoodCategories() {
        return referenceFoodCategories;
    }

    public void setReferenceFoodCategories(DatabaseReference referenceFoodCategories) {
        this.referenceFoodCategories = referenceFoodCategories;
    }

    public DatabaseReference getReferenceApumanque() {
        return referenceApumanque;
    }

    public void setReferenceApumanque(DatabaseReference referenceApumanque) {
        this.referenceApumanque = referenceApumanque;
    }

    public DatabaseReference getReferenceCategories() {
        return referenceCategories;
    }

    public void setReferenceCategories(DatabaseReference referenceCategories) {
        this.referenceCategories = referenceCategories;
    }

    public DatabaseReference getReferenceDiscounts() {
        return referenceDiscounts;
    }

    public void setReferenceDiscounts(DatabaseReference referenceDiscounts) {
        this.referenceDiscounts = referenceDiscounts;
    }
}