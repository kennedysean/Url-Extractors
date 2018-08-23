import re
import requests


WEBSITE_REGEX = re.compile(r'(https?://[a-zA-Z0-9]+\.[a-zA-Z0-9]+[a-zA-Z0-9._/~-]*)', re.VERBOSE)


def strip_site(html_text):
    sites = list()
    for groups in WEBSITE_REGEX.findall(html_text):
        if groups not in sites:
            sites.append(groups)
    sites.sort()
    return sites


def get_website(url):
    while True:
        try:
            website = requests.get(url)
            website.raise_for_status()
            return str(website.text)
        except Exception as e:
            print('There was an issue %s' % e)
            return None


url_name = input('Enter a site to strip of URLs: ')
while url_name:
    matches = strip_site(get_website(url_name))
    if len(matches) > 0:
        print('\n'.join(matches))
    else:
        print('No websites found')
    print()
    url_name = input('Enter a site to strip of URLs: ')
