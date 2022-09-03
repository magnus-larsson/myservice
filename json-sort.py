import json

# suffix='OK'
suffix='FAIL'

with open(f'src/main/resources/META-INF/reflect-config-{suffix}.json') as infile:
    data = json.load(infile)

data.sort(key=lambda x: x["name"])

with open(f'sorted-{suffix}.json', 'w') as outfile:
    outfile.write('[')
    for row in data:
        outfile.write(json.dumps(row['name']))
        outfile.write(',\n')
    outfile.write(']')
