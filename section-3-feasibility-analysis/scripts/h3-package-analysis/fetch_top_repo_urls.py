import psycopg2
output_file = "" #Path to your desired output location

def fetch_from_db():
    #This was designed to use a postgres sql database that held a dump of the ecosystems data
    try:
        connection = psycopg2.connect(
            user="",
            password="",
            host="",
            port="",
            database=""
        )

        cursor = connection.cursor()
        # Execute the query and fetch the first 10000 results
        
        query = """select name, repository_url 
    from (
            select distinct repository_url, name, dependent_repos_count
            from packages
            where ecosystem like 'maven' and repository_url not like 'private'
            )
            as subquery
            order by dependent_repos_count desc 
            limit 10000;""" #Limit can be modified to change the number of entries you desire

        cursor.execute(query)
        results = cursor.fetchall()
    except psycopg2.Error as e:
        print("DB Error")
        results = None
    finally:
        if connection is not None:
            cursor.close()
            connection.close()
        return(results)

def fix_gitbox_url(url):
    fixed_url = url.replace("?p=","/")
    return fixed_url
    
def get_all_urls(db_results):
    for i in range(len(db_results) - 1, -1, -1):
        name, url = db_results[i]

        if "github" in url:
            continue  # This tuple is good, move on to the next one
        elif "gitbox" in url or "gitlab" in url:
            # Store the result of fixed_gitbox_url into the second value
            db_results[i] = (name, fix_gitbox_url(url))
        else:
            # Remove the tuple from the list if it doesn't match any case
            db_results.pop(i)
    return db_results

def print_results(db_results):
    with open(output_file, 'w') as file:
        for result in db_results:
            name, url = result
            file.write(f"{url} {name}\n")

results = fetch_from_db()
results = get_all_urls(results)
print_results(results)





