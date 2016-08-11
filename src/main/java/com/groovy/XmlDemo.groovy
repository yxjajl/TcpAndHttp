package com.groovy

def CAR_RECORDS = '''
<records>
    <car name='HSV Maloo' make='Holden' year='2006'>
        <country>Australia</country>
        <record type='speed'>Production Pickup Truck with speed of 271kph</record>
    </car>
    <car name='P50' make='Peel' year='1962'>
        <country>Isle of Man</country>
        <record type='size'>Smallest Street-Legal Car at 99cm wide and 59 kg in weight</record>
    </car>
    <car name='Royale' make='Bugatti' year='1931'>
        <country>France</country>
        <record type='price'>Most Valuable Car at $15 million</record>
    </car>
</records> 
''';

def records = new XmlSlurper().parseText(CAR_RECORDS)// 3

assert 3 == records.car.size() //3
assert 10 ==  records.depthFirst().collect{ it }.size() //10

def firstRecord = records.car[0]

assert 'car'== firstRecord.name()
assert 'Holden' == firstRecord.@make.toString()
assert 'Australia'== firstRecord.country.text()

assert 2== records.car.findAll{ it.@make.toString().contains('e') }.size()
assert 2== records.car.findAll{ it.@make =~ '.*e.*'}.size()

assert ['Holden', 'Peel']== records.car.findAll{ it.country =~ '.*s.*a.*'}.@make.collect{ it.toString() }// types

assert['speed', 'size', 'price']== records.depthFirst().grep{ it.@type != ''}.'@type'*.toString()
assert['speed', 'size', 'price']== records.'**'.grep{ it.@type != ''}.'@type'*.toString()

def countryOne = records.car[1].country

assert 'Peel'== countryOne.parent().@make.toString()
assert 'Peel'== countryOne.'..'.@make.toString()

def names = records.car.list().sort{ it.@year.toInteger() }.'@name'*.toString()
assert['Royale', 'P50', 'HSV Maloo']== names

