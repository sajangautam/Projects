from flask import Flask,render_template,request
import os
import pymysql.cursors


app = Flask(__name__)

# Connect to the database
connection = pymysql.connect(host='demo-db.clfp2t0nwjvo.us-east-2.rds.amazonaws.com',
                             user='admin',
                             password='12345678',
                             db='sproutsdb',
                             cursorclass=pymysql.cursors.DictCursor)

@app.route('/')
def index():
    routes = [{'Header': 'Display the ITEM details',
               'Description': '',
               'url': '/1'},
              {'Header': ' Insert a new item ',
               'Description': '',
               'url': '/form/2'},
              {'Header': ' Update the item record ',
               'Description': '',
               'url': '/form/3'}, 
              {'Header': ' Delete the item record ',
               'Description': '',
               'url': '/form/4'}]
    return render_template('index.html',routes=enumerate(routes))



## Form 1


@app.route(f'/form/<int:route>')
def form1(route):
    try:
        ans =  render_template(f'{route}form.html')
    except :
        ans = render_template('404.html')
    return ans

@app.route('/1', methods=['GET'])
def form1response():
    with connection.cursor() as cursor:
        sql = '''
        select * from Item;
        '''
        print(sql)
        cursor.execute(sql)
        result = cursor.fetchall()
       
        col_names = ["ItemId", "Itemname", "Price"]
        values=[]
        for rows in result:
            values.append([x for x in rows.values()])
        print(values)
        print(col_names)
    cursor.close()
    return render_template('1.html', result=values, col_names=col_names)

@app.route('/2', methods=['POST'])
def form2response():
    itemid = request.form.get("itemid")
    itemname = request.form.get("itemname")
    itemprice = request.form.get("itemprice")
    with connection.cursor() as cursor:
        sql ='''
        insert into Item values ('{itemid}', '{itemname}', '{itemprice}');
        '''.format(itemid=itemid, itemname=itemname, itemprice=itemprice)
        print(sql)
        cursor.execute(sql)
    cursor.close()
    connection.commit()
    return render_template('ItemInsert.html')

@app.route('/3', methods=['POST'])
def form3response():
    itemid = request.form.get("itemid")
    itemname = request.form.get("itemname")
    with connection.cursor() as cursor:
        sql ='''
        update Item set Iname='{itemname}' where iId='{itemid}';
        '''.format(itemid=itemid, itemname=itemname)
        print(sql)
        cursor.execute(sql)
    cursor.close()
    connection.commit()
    return render_template('UpdateItem.html')


@app.route('/4', methods=['POST'])
def form4response():
    itemid = request.form.get("itemid")
    with connection.cursor() as cursor:
        sql ='''
        delete from Item where iId='{itemid}';
        '''.format(itemid=itemid)
        print(sql)
        cursor.execute(sql)
    cursor.close()
    connection.commit()
    return render_template('DeleteItem.html')